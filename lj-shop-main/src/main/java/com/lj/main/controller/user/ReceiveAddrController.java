package com.lj.main.controller.user;

import com.lj.common.annotation.Login;
import com.lj.common.enums.ErrorCode;
import com.lj.common.exception.LJShopException;
import com.lj.common.resp.PageResp;
import com.lj.common.util.BeanUtil;
import com.lj.common.util.ValidateUtil;
import com.lj.domain.dto.BaseUser;
import com.lj.domain.dto.ReceiveAddrDto;
import com.lj.main.req.ReceiveAddrReq;
import com.lj.main.util.ThreadLocalUtil;
import com.lj.service.user.ReceiveAddrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/myAddress")
public class ReceiveAddrController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveAddrController.class);

    @Autowired
    private ReceiveAddrService receiveAddrService;

    /**
     * 保存收货地址
     * @param req
     */
    @RequestMapping("/saveAddress")
    @Login
    public Long saveAddress(@RequestBody @Valid ReceiveAddrReq req){
        LOGGER.info("保存地址接口入参【{}】",req);
        BaseUser user = ThreadLocalUtil.currentAccount();
        req.setUserId(user.getId());
        return receiveAddrService.addReceiveAddr(BeanUtil.copyProperties(req, ReceiveAddrDto.class));
    }

    @RequestMapping("/editAddress")
    @Login
    public void editAddress(@RequestBody @Valid ReceiveAddrReq req){
        LOGGER.info("编辑接口入参【{}】",req);
        if (req.getId()==null || req.getId().equals(0L)){
            throw new LJShopException(ErrorCode.ARGS_MISS_ERROR,"收货地址唯一标示键为空");
        }
        BaseUser user = ThreadLocalUtil.currentAccount();
        req.setUserId(user.getId());
        receiveAddrService.editAddress(BeanUtil.copyProperties(req,ReceiveAddrDto.class));
    }
    @RequestMapping("/getAddressById")
    @Login
    public ReceiveAddrDto getAddressById(@RequestParam Long id){
    	LOGGER.info("getAddressById入参【{}】",id);
    	return receiveAddrService.getAddressById(id);
    }

    /**
     * 获取地址列表
     * @return
     */
    @RequestMapping("/getAddrList")
    @Login
    public List<ReceiveAddrDto> getAddrList(){
        BaseUser baseUser=ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        return receiveAddrService.listReceveAddr(baseUser.getId());
    }

    /**
     * 设为默认地址
     * @param id
     */
    @RequestMapping("/setDefaultAddr")
    @Login
    public void setDefaultAddr(@RequestParam Long id){
        BaseUser baseUser=ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        if (id==null || id.equals(0L)){
            throw new LJShopException(ErrorCode.ARGS_MISS_ERROR,"收货地址主键为空");
        }
        receiveAddrService.setDefaultAddr(id);
    }

    /**
     * 删除地址
     * @param id
     */
    @RequestMapping("/deleteAddr")
    @Login
    public void deleteAddr(@RequestParam Long id){
        BaseUser baseUser=ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        if (id==null || id.equals(0L)){
            throw new LJShopException(ErrorCode.ARGS_MISS_ERROR,"收货地址主键为空");
        }
        receiveAddrService.deleteAddr(id);
    }

    /**
     * 获取默认的地址
     * @return
     */
    @RequestMapping("/getDefaultAddr")
    @Login
    public ReceiveAddrDto getDefaultAddr(@RequestParam(required=false) Long id){
        BaseUser baseUser=ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        // 收货地址id不为空时
        if (id !=null){
            return receiveAddrService.getAddressById(id);
        }
        List<ReceiveAddrDto> addrDtoList = receiveAddrService.listReceveAddr(baseUser.getId());
        if (CollectionUtils.isEmpty(addrDtoList)){
            return null;
        }
        ReceiveAddrDto defaultAddr= addrDtoList.stream().filter(a->"0".equals(a.getIsDefault())).findAny().orElse(null);
        if (defaultAddr == null){
            defaultAddr = addrDtoList.get(0);
        }
        return defaultAddr;
    }

}
