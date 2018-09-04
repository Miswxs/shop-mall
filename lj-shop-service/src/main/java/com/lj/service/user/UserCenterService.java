package com.lj.service.user;

import com.google.common.collect.Lists;
import com.lj.common.enums.ErrorCode;
import com.lj.common.enums.UserTypeEnum;
import com.lj.common.exception.LJShopException;
import com.lj.common.resp.PageResp;
import com.lj.common.util.BeanUtil;
import com.lj.common.util.Constants;
import com.lj.db.mapper.*;
import com.lj.db.model.TAppCaptcha;
import com.lj.db.model.TMessageConfig;
import com.lj.db.model.TUser;
import com.lj.db.model.TUserFavorite;
import com.lj.domain.dto.GoodsDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 用户中心模块
 */
@Service
public class UserCenterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCenterService.class);

    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private TAppCaptchaMapper tAppCaptchaMapper;
    @Autowired
    private TMessageConfigMapper tMessageConfigMapper;
    @Autowired
    private TUserFavoriteMapper userFavoriteMapper;
    @Autowired
    private TGoodsMapper goodsMapper;

    /**
     * 用户注册
     * @param cellphone
     * @param pwd
     */
    @Transactional
    public void registerUser(String cellphone,String pwd,String ip,String userType){
        TUser tUser = tUserMapper.findByCellphone(cellphone);
        if(tUser !=null){
            throw new LJShopException(ErrorCode.INVALID_DATA,"该手机号已经注册过");
        }
        tUser=new TUser();
        tUser.setUserName("LJ"+cellphone.substring(7));
        tUser.setCellphone(cellphone);
        tUser.setLoginPwd(pwd);
        tUser.setIp(ip);
        tUser.setUserType(userType);
        tUserMapper.insert(tUser);
    }

    /**
     * 修改密码
     * @param cellphone
     * @param pwd
     */
    public void forgetPwd(String cellphone,String pwd){
        int result = tUserMapper.modifyPwd(cellphone, pwd);
        if (result >0) {
            return;
        }else {
            throw new LJShopException(ErrorCode.DATA_NOT_EXIST,"修改密码失败");
        }
    }

    /**
     * 校验验证码
     * @param cellphone
     * @return
     */
    public TAppCaptcha validateCaptcha(String cellphone){
        return tAppCaptchaMapper.getLastCaptchaByCellphone(cellphone);
    }

    /**
     * 根据手机类型获取发送短信配置
     * @param cellphone
     * @return
     */
    public TMessageConfig getMessageConfigByType(String cellphone){
        //国内手机号
        if (cellphone.matches(Constants.COMMON_MOBILE_REX)){
            return tMessageConfigMapper.getMessageConfigByType("1");
        }
        //港澳手机号
        if (cellphone.matches(Constants.HK_MOBILE_REX)){
            return tMessageConfigMapper.getMessageConfigByType("2");
        }
        return null;
    }

    /**
     * 发送短信，并接受验证码
     * @param cellphone
     */
    @Transactional
    public void sendMessage(String cellphone,TMessageConfig tMessageConfig){
        //生成验证码
        StringBuilder captcha=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=Constants.CAPTCHA_SRC.charAt(new Random().nextInt(Constants.CAPTCHA_SRC.length()));
            captcha.append(ch);
        }
        LOGGER.info("手机验证码为【{}】",captcha.toString());
        Calendar calendar = Calendar.getInstance();
        Date curDate = calendar.getTime();

        calendar.add(Calendar.MINUTE,Constants.CAPTCHA_EFFECTIVE_TIME);
        Date effectiveDate = calendar.getTime();

        TAppCaptcha tAppCaptcha = new TAppCaptcha();
        tAppCaptcha.setCaptcha(captcha.toString());
        tAppCaptcha.setCellphone(cellphone);
        tAppCaptcha.setEffectiveTime(effectiveDate);
        tAppCaptcha.setCreatedAt(curDate);
        tAppCaptcha.setCreatedBy(cellphone);
        tAppCaptcha.setUpdatedAt(curDate);
        tAppCaptcha.setUpdatedBy(cellphone);
        tAppCaptchaMapper.insert(tAppCaptcha);

        //发送短信
        StringBuilder sendUrl=new StringBuilder(tMessageConfig.getMessageUrl());
        try{
            String content = URLEncoder.encode(String.format(tMessageConfig.getMessageContent(),captcha.toString()), "GBK");// 发送内容
            sendUrl.append("?CorpID=").append(tMessageConfig.getMessageAccount())
                    .append("&Pwd=").append(tMessageConfig.getMessagePwd()).append("&Mobile=").append(cellphone)
                    .append("&Content=").append(content);
            LOGGER.info("发送短信地址【{}】",sendUrl.toString());
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;

            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(sendUrl.toString());
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            String result = EntityUtils.toString(entity);
            if (Integer.parseInt(result)>0){ //发送成功
                LOGGER.info("短信【{}】发送成功，返回【{}】",sendUrl,result);
            }else {
                LOGGER.info("短信【{}】发送失败，返回【{}】",sendUrl,result);
            }
        } catch (IOException e) {
            LOGGER.error("【{}】短信发送失败,{}",e);
        }

    }

    /**
     * 通过手机号获取用户
     * @param cellphone
     * @return
     */
    public TUser findUserByCellphone(String cellphone){
        TUser tUser = tUserMapper.findByCellphone(cellphone);
        return tUser;
    }

    /**
     * 查看我的资料
     * @param id
     * @return
     */
    public TUser findUserById(Long id){
        if (id==null || id.equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        return tUserMapper.findByUserId(id);
    }

    /**
     * 更新我的资料
     * @param tUser
     * @return
     */
    public void updateUserInfo (TUser tUser){
        if (tUser==null || tUser.getId() ==null || tUser.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        tUserMapper.editUserInfo(tUser);
    }

    /**
     * 添加到收藏夹
     * @param userId
     * @param goodsNo
     */
    public void addToMyFavirate(Long userId,String goodsNo,String favoriteType){
        TUserFavorite favorite = new TUserFavorite();
        favorite.setUserId(userId);
        favorite.setGoodsNo(goodsNo);
        favorite.setFavoriteType(favoriteType);
        int result = userFavoriteMapper.updateUserFavoriteDeleted(userId,goodsNo,"0",favoriteType);
        if (result > 0){
            return;
        }
        userFavoriteMapper.insert(favorite);
    }
    /**
     * 查看我的收藏
     * @param userId
     * @param type
     * @return
     */
    public PageResp<GoodsDto> listMyFavorite(Long userId,String type) {
    	Integer total = goodsMapper.countGoodsByUserId(userId, type);
    	List<GoodsDto> goods = Lists.newArrayList();
    	if (total>0) {
    		goods = BeanUtil.copyBeans(goodsMapper.listGoodsByUserId(userId, type),GoodsDto.class);
		}		
		return new PageResp<GoodsDto>(goods,total);
	}

	public PageResp<TUser> pageUserInfo(String searchTxt,Integer offset,Integer pageSize){
        Integer total = tUserMapper.countUserInfo(searchTxt);
        List<TUser> list = Lists.newArrayListWithCapacity(pageSize);
        if (total > 0){
            list = tUserMapper.pageUserInfo(searchTxt,offset,pageSize);
        }
        return new PageResp<TUser>(list,total);
    }

	public void deleteMyFavirate(Long userId,List<Long> gIds,String type){
        userFavoriteMapper.batchDeleteFavorite(userId,gIds,type);
    }

    public void deleteUserById(Long userId){
	    tUserMapper.deleteUserById(userId);
    }
}
