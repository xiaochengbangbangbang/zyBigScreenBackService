package org.jeecg.modules.com.bigScreen.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.com.bigScreen.Domain.ChannelLineDomain;
import org.jeecg.modules.com.bigScreen.Domain.MapDomain;
import org.jeecg.modules.com.bigScreen.entity.RvpNotify;

import java.util.List;
import java.util.Map;

/**
 * @Description: rvp_notify
 * @Author: jeecg-boot
 * @Date:   2023-05-21
 * @Version: V1.0
 */
public interface IRvpNotifyService extends IService<RvpNotify> {

    List<ChannelLineDomain> queryTotalReceipt(String startTime, String endTime , String channelName, boolean ifSuccess);


    List<ChannelLineDomain> queryCountAll(String endTime, boolean ifSuccess);


    List<ChannelLineDomain> queryIspCountAll(String endTime, Integer type, boolean ifSuccess);

    List<MapDomain> queryMapCountAll(String endTime, boolean ifSuccess);
}
