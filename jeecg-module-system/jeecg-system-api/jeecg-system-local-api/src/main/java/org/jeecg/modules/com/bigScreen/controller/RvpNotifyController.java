package org.jeecg.modules.com.bigScreen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.util.IpUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.com.bigScreen.Domain.ChannelLineDomain;
import org.jeecg.modules.com.bigScreen.Domain.MapDomain;
import org.jeecg.modules.com.bigScreen.entity.RvpNotify;

import org.jeecg.modules.com.bigScreen.service.IRvpNotifyService;
import org.jeecg.modules.com.bigScreen.service.ITempTimeService;
import org.jeecg.modules.com.bigScreen.vo.ProvinceMapVo;
import org.jeecg.modules.com.bigScreen.vo.SuccessProportionVo;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: rvp_notify
 * @Author: jeecg-boot
 * @Date: 2023-05-21
 * @Version: V1.0
 */
@Api(tags = "rvp_notify")
@RestController
@RequestMapping("/bigScreen/rvpNotify")
@Slf4j
public class RvpNotifyController extends JeecgController<RvpNotify, IRvpNotifyService> {
    @Autowired
    private IRvpNotifyService rvpNotifyService;

    @Resource
    private RedisUtil redisUtil;

    private static Logger logger = LoggerFactory.getLogger(RvpNotifyController.class);

    /**
     * 分页列表查询
     *
     * @param rvpNotify
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "rvp_notify-分页列表查询")
    @ApiOperation(value = "rvp_notify-分页列表查询", notes = "rvp_notify-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<RvpNotify>> queryPageList(RvpNotify rvpNotify,
                                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                  HttpServletRequest req) {
        QueryWrapper<RvpNotify> queryWrapper = QueryGenerator.initQueryWrapper(rvpNotify, req.getParameterMap());
        Page<RvpNotify> page = new Page<RvpNotify>(pageNo, pageSize);
        IPage<RvpNotify> pageList = rvpNotifyService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param rvpNotify
     * @return
     */
    @AutoLog(value = "rvp_notify-添加")
    @ApiOperation(value = "rvp_notify-添加", notes = "rvp_notify-添加")
//    @RequiresPermissions("com.bigScreen:rvp_notify:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody RvpNotify rvpNotify) {
        rvpNotifyService.save(rvpNotify);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param rvpNotify
     * @return
     */
    @AutoLog(value = "rvp_notify-编辑")
    @ApiOperation(value = "rvp_notify-编辑", notes = "rvp_notify-编辑")
//    @RequiresPermissions("com.bigScreen:rvp_notify:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody RvpNotify rvpNotify) {
        rvpNotifyService.updateById(rvpNotify);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "rvp_notify-通过id删除")
    @ApiOperation(value = "rvp_notify-通过id删除", notes = "rvp_notify-通过id删除")
    @RequiresPermissions("com.bigScreen:rvp_notify:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        rvpNotifyService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "rvp_notify-批量删除")
    @ApiOperation(value = "rvp_notify-批量删除", notes = "rvp_notify-批量删除")
    @RequiresPermissions("com.bigScreen:rvp_notify:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.rvpNotifyService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "rvp_notify-通过id查询")
    @ApiOperation(value = "rvp_notify-通过id查询", notes = "rvp_notify-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<RvpNotify> queryById(@RequestParam(name = "id", required = true) String id) {
        RvpNotify rvpNotify = rvpNotifyService.getById(id);
        if (rvpNotify == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(rvpNotify);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rvpNotify
     */
    @RequiresPermissions("com.bigScreen:rvp_notify:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RvpNotify rvpNotify) {
        return super.exportXls(request, rvpNotify, RvpNotify.class, "rvp_notify");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("com.bigScreen:rvp_notify:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RvpNotify.class);
    }

    //折线图
    @GetMapping(value = "/success/{queryChannel}")
    public JSONObject success(@PathVariable String queryChannel, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try{
        JSONObject resultObject = new JSONObject();
        String[] categories = new String[5];
        String[] doubles = new String[5];
        String[] sms = new String[5];
        List<ChannelLineDomain> alls = null;
        List<ChannelLineDomain> success = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        List<Map<String, String>> times = new ArrayList<>(5);

        String endTime = df.format(date);
//        String endTime = "2023-04-26 10:07";
        categories[4] = endTime.substring(11);
//
        Calendar c = Calendar.getInstance();

        c.add(Calendar.MINUTE, -1);
        date = c.getTime(); //这个时间就是日期往后推一天的结果
        String startTime = df.format(date);
//        String startTime = "2023-04-26 10:06";
//
        Map<String, String> timeMap = new HashMap<>();
        timeMap.put(startTime, endTime);
        times.add(timeMap);

//        HashMap<String, String> timeMap1 = new HashMap<>();
//        HashMap<String, String> timeMap2 = new HashMap<>();
//        HashMap<String, String> timeMap3 = new HashMap<>();
//        HashMap<String, String> timeMap4 = new HashMap<>();
//        timeMap1.put("2023-04-26 10:05", "2023-04-26 10:06");
//        categories[3] = "2023-04-26 10:06".substring(11);
//        timeMap2.put("2023-04-26 10:04", "2023-04-26 10:05");
//        categories[2] = "2023-04-26 10:05".substring(11);
//        timeMap3.put("2023-04-26 10:03", "2023-04-26 10:04");
//        categories[1] = "2023-04-26 10:04".substring(11);
//        timeMap4.put("2023-04-26 10:02", "2023-04-26 10:03");
//        categories[0] = "2023-04-26 10:03".substring(11);
//        times.add(timeMap1);
//        times.add(timeMap2);
//        times.add(timeMap3);
//        times.add(timeMap4);
//        Collections.reverse(times);

        String keyName = "Line:" + queryChannel + ":" + startTime;
//        String keyName = "Line:" + queryChannel + ":" + endTime;
        Gson gson = new Gson();

        if(redisUtil.hasKey(keyName)){
            Object o =  redisUtil.get(keyName);
            List<String> jsonArrayToStringList = JSONObject.parseArray(o.toString(),String.class);
            String[] dateList = gson.fromJson(jsonArrayToStringList.get(0), String[].class);
            String[] numList = gson.fromJson(jsonArrayToStringList.get(1), String[].class);
            String[] smsList = gson.fromJson(jsonArrayToStringList.get(2), String[].class);
            for (int i = 0; i < dateList.length -1; i++) {
                categories[i] = dateList[i+1].toString();
            }
            for (int i = 0; i < numList.length -1; i++) {
                doubles[i] = numList[i+1].toString();
            }
            for (int i = 0; i < smsList.length -1; i++) {
                sms[i] = smsList[i+1].toString();
            }
            JSONObject jsonObject11 = dealLine(queryChannel, startTime, endTime, alls, success);
            alls = JSONObject.parseArray(jsonObject11.getJSONArray("alls").toJSONString(), ChannelLineDomain.class);
            success = JSONObject.parseArray(jsonObject11.getJSONArray("success").toJSONString(), ChannelLineDomain.class);
            categories[4] = endTime.substring(11);
            for (int j = alls.size()-2; j < alls.size(); j++) {
                Long big = Long.valueOf(alls.get(j).getCount());
                Long small = Long.valueOf(success.get(j).getCount());
                Double percent = getPercent(small, big);
                if (alls.get(j).getShortSms().equals("0")) {
                    doubles[4] = String.valueOf(percent);
                } else {
                    sms[4] = String.valueOf(percent);
                }
            }
            redisUtil.del(keyName);
        }else {
            int k = 3;

            for (int i = 2; i <= 5; i++) {
                endTime = startTime;
                categories[k] = endTime.substring(11);
                k--;
                c.add(Calendar.MINUTE, -1);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
                date = c.getTime();
                startTime = df.format(date);
                Map<String, String> timeMap1 = new HashMap<>();
                timeMap1.put(startTime, endTime);
                times.add(timeMap1);
            }
            Collections.reverse(times);

            for (int i = 0; i < times.size(); i++) {

                Map<String, String> stringStringMap = times.get(i);
                for (Map.Entry<String, String> entry : stringStringMap.entrySet()) {
                    startTime = entry.getKey();
                    endTime = entry.getValue();
                }
                JSONObject jsonObject22 = dealLine(queryChannel, startTime, endTime, alls, success);
                alls = JSONObject.parseArray(jsonObject22.getJSONArray("alls").toJSONString(), ChannelLineDomain.class);
                success = JSONObject.parseArray(jsonObject22.getJSONArray("success").toJSONString(), ChannelLineDomain.class);
                for (int j = 0; j < alls.size(); j++) {
                    Long big = Long.valueOf(alls.get(j).getCount());
                    Long small = Long.valueOf(success.get(j).getCount());
                    Double percent = getPercent(small, big);
                    if (alls.get(j).getShortSms().equals("0")) {
                        doubles[i] = String.valueOf(percent);
                    } else {
                        sms[i] = String.valueOf(percent);
                    }
                }
            }
            }



        resultObject.put("dateList", categories);
        resultObject.put("numList", doubles);
        resultObject.put("smsList", sms);

        jsonObject.put("data", resultObject);
        String name = null;
        for (Map.Entry<String, String> entry : times.get(4).entrySet()) {
            name = entry.getValue();
        }
        keyName = "Line:" + queryChannel + ":" + name;
        List<String[]> strings = new ArrayList<>();
        strings.add(categories);
        strings.add(doubles);
        strings.add(sms);
        String json = gson.toJson(strings);
        redisUtil.set(keyName,json,90);
        jsonObject.put("code", 200);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return jsonObject;
    }

    //胶囊图，各通道质量
    @GetMapping(value = "/countByType/{type}")
    public JSONObject countByType(@PathVariable String type, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            String endTime = df.format(date);
//        String endTime = "2023-04-26";

            List<ChannelLineDomain> maps = rvpNotifyService.queryCountAll(endTime, false);
            List<ChannelLineDomain> success = rvpNotifyService.queryCountAll(endTime, true);


            String[] strings = new String[5];
            strings[0] = "xdr_msp_call_in_queue";
            strings[1] = "xdr_migu_queue";
            strings[2] = "xdr_msp_queue";
            strings[3] = "xdr_smgp_proxy_queue";
            strings[4] = "xdr_msp_free_queue";

            List<ChannelLineDomain> newMap = new ArrayList<>(maps);
            List<ChannelLineDomain> newSuccessMap = new ArrayList<>(success);

            if (maps.size() == 0) {
                addChannelQua(strings, maps, success, true);
            } else if (maps.size() <= 5) {
                if (success.size() == 0) {
                    addChannelQua(strings, maps, success, false);
                } else {
                    maps.clear();
                    success.clear();
                    for (int i1 = 0; i1 < strings.length; i1++) {
                        String s = strings[i1];
                        ChannelLineDomain anyAll = newMap.stream().filter(all -> s.equals(all.getShortSms())).findAny().orElse(null);
                        ChannelLineDomain anySuc = newSuccessMap.stream().filter(all -> s.equals(all.getShortSms())).findAny().orElse(null);
                        if (anyAll != null) {
                            maps.add(i1, anyAll);
                        } else {
                            maps.add(i1, new ChannelLineDomain(String.valueOf(0), s));
                        }
                        if (anySuc != null) {
                            success.add(i1, anySuc);
                        } else {
                            success.add(i1, new ChannelLineDomain(String.valueOf(0), s));
                        }
                    }
                }
            }

            Double[] percents = new Double[maps.size()];
            String[] times = new String[maps.size()];
            Long[] alls = new Long[maps.size()];
            for (int i = 0; i < maps.size(); i++) {
                Long big = Long.valueOf(maps.get(i).getCount());
                Long small = Long.valueOf(success.get(i).getCount());
                Double percent = getPercent(small, big);
                int firstIndex = maps.get(i).getShortSms().indexOf("_");
                int lastIndex = maps.get(i).getShortSms().lastIndexOf("_");

                times[i] = maps.get(i).getShortSms().substring(firstIndex+1,lastIndex);
                percents[i] = percent;
                alls[i] = big;
            }

            jsonObject.put("alls", alls);
            jsonObject.put("times", times);
            jsonObject.put("percents", percents);

            jsonObject.put("code", 200);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return jsonObject;
    }


    @GetMapping({"/SuccessByIsp"})
    public SuccessProportionVo[] SuccessByIsp(@RequestParam(name = "id", required = false) String id) {
        SuccessProportionVo[] successProportionVos = new SuccessProportionVo[6];
        try{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String endTime = df.format(date);
//        String endTime = "2023-04-25";
        String[] strings = new String[3];
        strings[0] = "CTCC";
        strings[1] = "CMCC";
        strings[2] = "CUCC";
        int arrCnt = 0;

        String name = null;
        String name1 = null;
        for (int i = 0; i < 2; i++) {
            List<ChannelLineDomain> maps = this.rvpNotifyService.queryIspCountAll(endTime, Integer.valueOf(i), false);
            List<ChannelLineDomain> success = this.rvpNotifyService.queryIspCountAll(endTime, Integer.valueOf(i), true);
            List<ChannelLineDomain> newMap = new ArrayList<>(maps);
            List<ChannelLineDomain> newSuccessMap = new ArrayList<>(success);
            if (maps.size() == 0) {
                addChannelQua(strings, maps, success, true);
            } else  {
                if (success.size() == 0) {
                    addChannelQua(strings, maps, success, false);
                } else {
                    maps.clear();
                    success.clear();
                    for (int i1 = 0; i1 < strings.length; i1++) {
                        String s = strings[i1];
                        ChannelLineDomain anyAll = newMap.stream().filter(all -> s.equals(all.getShortSms())).findAny().orElse(null);
                        ChannelLineDomain anySuc = newSuccessMap.stream().filter(all -> s.equals(all.getShortSms())).findAny().orElse(null);
                        if (anyAll != null) {
                            maps.add(i1, anyAll);
                        } else {
                            maps.add(i1, new ChannelLineDomain(String.valueOf(0), s));
                        }
                        if (anySuc != null) {
                            success.add(i1, anySuc);
                        } else {
                            success.add(i1, new ChannelLineDomain(String.valueOf(0), s));
                        }
                    }
                }
            }
            for (int j = 0; j < maps.size(); j++) {
                Long big = Long.valueOf((maps.get(j)).getCount());
                Long small = Long.valueOf((success.get(j)).getCount());
                Double percent = getPercent(small, big);
                if (i == 0) {
                    name1 = "(闪信)";
                } else {
                    name1 = "(短信)";
                }
                if ((maps.get(j)).getShortSms().equals("CTCC")) {
                    name = "电信";
                } else if ((maps.get(j)).getShortSms().equals("CUCC")) {
                    name = "联通";
                } else {
                    name = "移动";
                }
                SuccessProportionVo provinceVo = new SuccessProportionVo(name + name1, percent, Double.valueOf(100 - percent.doubleValue()), small, big - small);
                successProportionVos[arrCnt] = provinceVo;
                arrCnt++;
            }
        }
    }catch (Exception e){
        logger.error(e.getMessage());
    }
        return successProportionVos;
    }

    //地图
    @GetMapping(value = "/map")
    public ProvinceMapVo[] map(@RequestParam(name = "id", required = false) String id) {
        String[] provinces = {"北京", "天津", "上海", "重庆", "河北", "山西", "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "海南", "四川", "贵州", "云南", "陕西", "甘肃", "青海", "台湾", "内蒙古", "广西", "西藏", "宁夏", "新疆"};

        ProvinceMapVo[] successProportionVos = new ProvinceMapVo[provinces.length];
        try{

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String endTime = df.format(date);
//        String endTime = "2023-04-26";

        String[] isps = new String[3];
        isps[0] = "CUCC";
        isps[1] = "CTCC";
        isps[2] = "CMCC";



        List<MapDomain> maps = rvpNotifyService.queryMapCountAll(endTime, false);
        List<MapDomain> success = rvpNotifyService.queryMapCountAll(endTime, true);

        int len = provinces.length * isps.length;
        if (maps.size() == 0) {
            addMap(provinces, isps, maps, success, true);
        } else if (maps.size() <= len) {
            if (success.size() == 0) {
                addMap(provinces, isps, maps, success, false);
            } else {
                for (int i = 0; i < provinces.length; i++) {
                    String name = provinces[i];
                    List<MapDomain> collect = maps.stream().filter(p -> p.getProvince().equals(name)).collect(Collectors.toList());
                    List<MapDomain> sucCollect = success.stream().filter(p -> p.getProvince().equals(name)).collect(Collectors.toList());
                    for (int j = 0; j < isps.length; j++) {
                        String name1 = isps[j];
                        if (collect != null && collect.size() > 0) {
                            MapDomain anyAll = collect.stream().filter(all -> name1.equals(all.getIsp())).findAny().orElse(null);
                            if (anyAll == null) {
                                maps.add(new MapDomain(name, name1, "0"));
                            }
                        } else {
                            maps.add(new MapDomain(name, name1, "0"));
                        }
                        if (sucCollect != null && collect.size() > 0) {
                            MapDomain subAny = sucCollect.stream().filter(all -> name1.equals(all.getIsp())).findAny().orElse(null);
                            if (subAny == null) {
                                success.add(new MapDomain(name, name1, "0"));
                            }
                        } else {
                            success.add(new MapDomain(name, name1, "0"));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < provinces.length; i++) {
            String pro = provinces[i];
            //补充完缺失数据开始封装
            List<MapDomain> newMaps = maps.stream().filter(p -> p.getProvince().equals(pro)).collect(Collectors.toList());
            List<MapDomain> newSuccess = success.stream().filter(p -> p.getProvince().equals(pro)).collect(Collectors.toList());
            ProvinceMapVo provinceMapVo = new ProvinceMapVo();
            provinceMapVo.setName(pro);
            for (int i1 = 0; i1 < isps.length; i1++) {
                String tempName = isps[i1];
                MapDomain any = newMaps.stream().filter(all -> tempName.equals(all.getIsp())).findAny().orElse(null);
                MapDomain suc = newSuccess.stream().filter(all -> tempName.equals(all.getIsp())).findAny().orElse(null);
                switch (tempName) {
                    case "CUCC":
                        provinceMapVo.setValue(Long.valueOf(any.getCount()));
                        Double percent = getPercent(Long.valueOf(suc.getCount()), Long.valueOf(any.getCount()));
                        provinceMapVo.setPercentage(percent + "%");
                        break;
                    case "CTCC":
                        provinceMapVo.setValue2(Long.valueOf(any.getCount()));
                        Double percent1 = getPercent(Long.valueOf(suc.getCount()), Long.valueOf(any.getCount()));
                        provinceMapVo.setPercentage2(percent1 + "%");
                        break;
                    case "CMCC":
                        provinceMapVo.setValue3(Long.valueOf(any.getCount()));
                        Double percent2 = getPercent(Long.valueOf(suc.getCount()), Long.valueOf(any.getCount()));
                        provinceMapVo.setPercentage3(percent2 + "%");
                        break;
                }
            }
            successProportionVos[i] = provinceMapVo;
        }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return successProportionVos;
    }

    private static Double getPercent(Long x, Long y) {
        if (x == 0) {
            return Double.valueOf(0);
        }
        double d1 = x * 1.0;
        double d2 = y * 1.0;
//        NumberFormat percentInstance = NumberFormat.getPercentInstance();
//        // 设置保留几位小数，这里设置的是保留两位小数
//        percentInstance.setMinimumFractionDigits(2);
        String format = String.format("%.2f", d1 / d2 * 100);
        return Double.valueOf(format);
    }

    private static void addChannelLine(List<ChannelLineDomain> alls, List<ChannelLineDomain> success, boolean isAll) {
        for (int i1 = 0; i1 < 2; i1++) {
            String s = String.valueOf(i1);
            ChannelLineDomain channelLineDomain = new ChannelLineDomain(String.valueOf(0), s);
            success.add(i1, channelLineDomain);
            if (isAll) {
                alls.add(i1, channelLineDomain);
            }
        }
    }

    private static void addChannelQua(String[] qua, List<ChannelLineDomain> alls, List<ChannelLineDomain> success, boolean isAll) {
        for (int i1 = 0; i1 < qua.length; i1++) {
            ChannelLineDomain channelLineDomain = new ChannelLineDomain(String.valueOf(0), qua[i1]);
            success.add(i1, channelLineDomain);
            if (isAll) {
                alls.add(i1, channelLineDomain);
            }
        }
    }

    private static void addMap(String[] provinces, String[] isps, List<MapDomain> alls, List<MapDomain> success, boolean isAll) {
        for (int i1 = 0; i1 < provinces.length; i1++) {
            for (int i = 0; i < isps.length; i++) {
                MapDomain mapDomain = new MapDomain(provinces[i1], isps[i], "0");
                alls.add(mapDomain);
                if (isAll) {
                    success.add(mapDomain);
                }
            }
        }
    }

    private JSONObject dealLine(String queryChannel,String startTime,String endTime,List<ChannelLineDomain> alls,List<ChannelLineDomain> success){
        queryChannel = queryChannel.trim();
        alls = rvpNotifyService.queryTotalReceipt(startTime, endTime, queryChannel, false);
        success = rvpNotifyService.queryTotalReceipt(startTime, endTime, queryChannel, true);

        List<ChannelLineDomain> newMap = new ArrayList<>(alls);
        List<ChannelLineDomain> newSuccessMap = new ArrayList<>(success);

        if (alls.size() == 0) {
            addChannelLine(alls, success, true);
        } else if (alls.size() <= 2) {
            if (success.size() == 0) {
                addChannelLine(alls, success, false);
            } else {
                alls.clear();
                success.clear();
                for (int i1 = 0; i1 < 2; i1++) {
                    String s = String.valueOf(i1);
                    ChannelLineDomain anyAll = newMap.stream().filter(all -> s.equals(all.getShortSms())).findAny().orElse(null);
                    ChannelLineDomain anySuc = newSuccessMap.stream().filter(all -> s.equals(all.getShortSms())).findAny().orElse(null);
                    if (anyAll != null) {
                        alls.add(i1, anyAll);
                    } else {
                        alls.add(i1, new ChannelLineDomain(String.valueOf(0), s));
                    }
                    if (anySuc != null) {
                        success.add(i1, anySuc);
                    } else {
                        success.add(i1, new ChannelLineDomain(String.valueOf(0), s));
                    }
                }
            }

        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("alls",alls);
        jsonObject.put("success",success);
        return jsonObject;
    }
}
