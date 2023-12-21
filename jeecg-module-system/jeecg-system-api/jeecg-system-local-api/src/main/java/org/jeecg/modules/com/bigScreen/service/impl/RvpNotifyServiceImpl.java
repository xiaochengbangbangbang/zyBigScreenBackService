package org.jeecg.modules.com.bigScreen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.jeecg.modules.com.bigScreen.Domain.ChannelLineDomain;
import org.jeecg.modules.com.bigScreen.Domain.MapDomain;
import org.jeecg.modules.com.bigScreen.entity.RvpNotify;
import org.jeecg.modules.com.bigScreen.mapper.RvpNotifyMapper;
import org.jeecg.modules.com.bigScreen.service.IRvpNotifyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: rvp_notify
 * @Author: jeecg-boot
 * @Date:   2023-05-21
 * @Version: V1.0
 */
@Service
public class RvpNotifyServiceImpl extends ServiceImpl<RvpNotifyMapper, RvpNotify> implements IRvpNotifyService {

    @Override
    public List<ChannelLineDomain> queryTotalReceipt(String startTime, String endTime, String channelName , boolean ifSuccess) {
//        QueryWrapper<RvpNotify> wrapper1 = new QueryWrapper<>();
//        wrapper1.select("short_sms", "count(*)");
//        wrapper1.ge("send_time",startTime).le("send_time",endTime);
//        if(ifSuccess){
//            wrapper1 .and(wr ->{
//                wr.eq("deliver_code","0").
//                        or().eq("deliver_code","deliver");
//            });
//        }
//        wrapper1.eq("channel_name",channelName)
//                .groupBy("short_sms")
//                .orderByAsc("short_sms");
//        return baseMapper.selectMaps(wrapper1);
        return baseMapper.selectChannelLine( startTime,endTime,channelName,ifSuccess);
    }


    @Override
    public List<ChannelLineDomain> queryCountAll(String endTime, boolean ifSuccess) {
//        QueryWrapper<RvpNotify> wrapper = new QueryWrapper<>();
//        wrapper.select(type, "count(*)")
//                .ge("send_time", endTime);
//        if(ifSuccess){
//            wrapper.and(wr ->{
//                wr.eq("deliver_code","0").
//                        or().eq("deliver_code","deliver");
//            });
//        }
//                wrapper.groupBy(type)
//                .orderByAsc(type);
//        List<Map<String, Object>> maps = baseMapper.selectMaps(wrapper);
        List<ChannelLineDomain> maps = baseMapper.selectChannelQua(endTime,ifSuccess);

        return maps;
    }


    @Override
    public List<ChannelLineDomain> queryIspCountAll(String endTime, Integer type, boolean ifSuccess) {
//        QueryWrapper<RvpNotify> wrapper = new QueryWrapper<>();
//        wrapper.select(queryType,"count(*)");
//        wrapper.ge("send_time", endTime)
//                .eq("short_sms",type)
//                .isNotNull(queryType);
//
//        if(ifSuccess){
//            wrapper.and(wr ->{
//                wr.eq("deliver_code","0").
//                        or().eq("deliver_code","deliver");
//            });
//        }
//                wrapper.groupBy(queryType)
//                        .orderByAsc(queryType);
//        List<Map<String, Object>> all = baseMapper.selectMaps(wrapper);
        List<ChannelLineDomain> channelLineDomains = baseMapper.countByIsp(endTime, type, ifSuccess);
        return channelLineDomains;
    }

    @Override
    public List<MapDomain> queryMapCountAll(String endTime, boolean ifSuccess) {

        return baseMapper.queryMapCountAll(endTime,ifSuccess);
    }

    @Override
    public Long queryCountAllByWy(String endTime) {
        return baseMapper.queryCountAllByWy(endTime);
    }

    @Override
    public Long querySuccessByWy(String endTime, boolean ifSuccess) {
        return baseMapper.querySuccessByWy(endTime,ifSuccess);
    }


}
