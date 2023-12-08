package org.jeecg.modules.com.bigScreen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.com.bigScreen.Domain.ChannelLineDomain;
import org.jeecg.modules.com.bigScreen.Domain.MapDomain;
import org.jeecg.modules.com.bigScreen.entity.RvpNotify;

import java.util.List;


/**
 * @Description: rvp_notify
 * @Author: jeecg-boot
 * @Date:   2023-05-21
 * @Version: V1.0
 */
public interface RvpNotifyMapper extends BaseMapper<RvpNotify> {
    List<ChannelLineDomain> selectChannelLine(String startTime, String endTime, String channelName, boolean ifSuccess);

    List<ChannelLineDomain> selectChannelQua(String endTime, boolean ifSuccess);

    List<ChannelLineDomain> countByIsp(String endTime, Integer type, boolean ifSuccess);

    List<MapDomain> queryMapCountAll(String endTime, boolean ifSuccess);

//    List<Map<String, Long>> queryTotalReceipt(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
