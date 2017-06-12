package com.youbi.app.core.admin.controller;

import com.youbi.app.core.utils.JsonUtils;
import com.youbi.app.core.vo.RoadwayDropList;
import com.youbi.app.model.County;
import com.youbi.app.model.Signal;
import com.youbi.app.model.Unit;
import com.youbi.app.model.User;
import com.youbi.app.service.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hubin1 on 2017/4/14.
 */

@Controller
@RequestMapping("/management/signal")
public class SignalController extends BaseController {

    @Autowired
    SignalService signalService;

    @Autowired
    SignalTypeService signalTypeService;

    @Autowired
    CountyService countyService;

    @Autowired
    RoadwayService roadwayService;

    @Autowired
    UnitService unitService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page(Model model) {
        return "/admin/signal/signalList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String list(HttpServletRequest request) {
        String signalTypeId1 = request.getParameter("signalTypeId");
        int signalTypeId = 0;
        if (!StringUtils.isEmpty(signalTypeId1)) {
            signalTypeId = Integer.parseInt(signalTypeId1);
        }
        String countyId1 = request.getParameter("countyId");
        int countyId = 0;
        if (!StringUtils.isEmpty(countyId1)) {
            countyId = Integer.parseInt(countyId1);
        }
        String unitId1 = request.getParameter("unitId");
        int unitId = 0;
        if (!StringUtils.isEmpty(unitId1)) {
            unitId = Integer.parseInt(unitId1);
        }
        String roadId1 = request.getParameter("roadId");
        int roadId = 0;
        if (!StringUtils.isEmpty(roadId1)) {
            roadId = Integer.parseInt(roadId1);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String beginTime1 = request.getParameter("beginTime");
        Date beginTime = null;
        if (!StringUtils.isEmpty(beginTime1)) {
            try {
                beginTime = sdf.parse(beginTime1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String endTime1 = request.getParameter("endTime");
        Date endTime = null;
        if (!StringUtils.isEmpty(endTime1)) {
            try {
                endTime = sdf.parse(endTime1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String isDamaged1 = request.getParameter("isDamaged");
        int isDamaged = 0;
        if (!StringUtils.isEmpty(isDamaged1)) {
            isDamaged = Integer.parseInt(isDamaged1);
        }
        String isUsed1 = request.getParameter("isUsed");
        int isUsed = 0;
        if (!StringUtils.isEmpty(isUsed1)) {
            isUsed = Integer.parseInt(isUsed1);
        }
        String isConfirmed1 = request.getParameter("isConfirmed");
        int isConfirmed = 0;
        if(!StringUtils.isEmpty(isConfirmed1)){
            isConfirmed = Integer.parseInt(isConfirmed1);
        }
        String orderStatus1 = request.getParameter("orderStatus");
        boolean orderStatus = false;
        if (!StringUtils.isEmpty(orderStatus1)) {
            orderStatus = Boolean.parseBoolean(orderStatus1);
        }
        String pageNumber1 = request.getParameter("pageNumber");
        int pageNumber = 0;
        if (!StringUtils.isEmpty(pageNumber1)) {
            pageNumber = Integer.parseInt(pageNumber1);
        }
        int start = getStart(pageNumber);
        List<Signal> signals = signalService.findList(signalTypeId, countyId, unitId, roadId, beginTime, endTime, isDamaged,
                isUsed,isConfirmed, orderStatus, start, pageSize);
        Map<String, Integer> pageList = new HashedMap();
        Map<String, Object> map = new HashedMap();
        pageList = getPageList(pageNumber, signalService.findCount(signalTypeId, countyId, unitId, roadId, beginTime, endTime, isDamaged,
                isUsed, isConfirmed));
        map.put("currentList", signals);
        map.put("currentPages", pageList);
        return JsonUtils.toJson(map);
    }

    @RequestMapping(value="/editPage", method = {RequestMethod.GET})
    public String addPage(@Param("id") int id,Model model){
        Signal signal = new Signal();
        if(id == 0){
            model.addAttribute("signal", signal);
        }else{
            model.addAttribute("signal", signalService.findById(id));
        }
        model.addAttribute("signalTypeList", signalTypeService.findAll());
        model.addAttribute("roadwayList", roadwayService.findDropList());
        model.addAttribute("unitList", unitService.findLevelOne(0));
        return "/admin/signal/signalPage";
    }

    @RequestMapping(value="/save", method = {RequestMethod.POST})
    @ResponseBody
    public String save(Signal signal){
        if(signal.getSignalId() == null){
            User user = getCurrentUser().getUser();
            if(user.getRole().getRoleName().equals("CAPTAIN")){
                Unit unit = new Unit();
                unit.setUnitId(user.getAdminunit().getUnitId());
                signal.setUnit(unit);
            }
            signal.setTypeId(1);
            signal.setUser(user);
            signal.setCounty(countyService.findById(unitService.findById(signal.getUnit().getUnitId()).getCounty().getId())); //根据单位注入区县信息
            signal.setIsConfirmed(true);
            signal.setCreateTime(new Date());
            signal.setIsUsed(true);
            signal.setIsDamaged(false);
        }
        try{
            signalService.save(signal);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE})
    @ResponseBody
    public String delete(@PathVariable("id") int id){
        return "";
    }
}
