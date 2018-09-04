package com.lj.service.user;

import com.lj.common.util.BeanUtil;
import com.lj.db.mapper.TReceiveAddrMapper;
import com.lj.db.model.TReceiveAddr;
import com.lj.domain.dto.ReceiveAddrDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiveAddrService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveAddrService.class);

    @Autowired
    private TReceiveAddrMapper receiveAddrMapper;
    /**
     * 新增收货地址
     * @param receiveAddr
     */
    public Long addReceiveAddr(ReceiveAddrDto receiveAddr){
        TReceiveAddr tReceiveAddr = BeanUtil.copyProperties(receiveAddr, TReceiveAddr.class);
        receiveAddrMapper.insert(tReceiveAddr);
        return tReceiveAddr.getId();
    }

    /**
     * 修改地址
     * @param receiveAddrDto
     */
    public void editAddress(ReceiveAddrDto receiveAddrDto){
        receiveAddrMapper.update(BeanUtil.copyProperties(receiveAddrDto,TReceiveAddr.class));
    }
    /**
     * 通过主键id获取地址信息
     * @param id
     */
    public ReceiveAddrDto getAddressById(Long id){
        return BeanUtil.copyProperties(receiveAddrMapper.findAddressById(id),ReceiveAddrDto.class);
    }

    /**
     * 查询所有地址：默认20条
     * @param userId
     * @return
     */
    public List<ReceiveAddrDto> listReceveAddr(Long userId){
        List<TReceiveAddr> addrList = receiveAddrMapper.findAddrByUserId(userId);
        return BeanUtil.copyBeans(addrList,ReceiveAddrDto.class);
    }

    /**
     * 设置默认地址
     * @param id
     */
    public void setDefaultAddr(Long id){
        // 处理之前默认的地址
        receiveAddrMapper.setNotDefaultAddr();
        // 更新默认地址
        receiveAddrMapper.setDefaultAddr(id);
    }

    /**
     * 设置默认地址
     * @param id
     */
    public void deleteAddr(Long id){
        receiveAddrMapper.deleteAddr(id);
    }
}
