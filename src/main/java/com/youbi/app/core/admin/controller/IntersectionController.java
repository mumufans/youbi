package com.youbi.app.core.admin.controller;

import com.youbi.app.core.security.Account;
import com.youbi.app.core.utils.JsonUtils;
import com.youbi.app.core.utils.MapUtils;
import com.youbi.app.core.utils.TableUtils;
import com.youbi.app.core.vo.RoadwayDropList;
import com.youbi.app.model.*;
import com.youbi.app.service.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by ASUS on 2017/4/10.
 */

@Controller
@RequestMapping("/intersection")
public class IntersectionController extends BaseController {

    @Autowired
    IntersectionService intersectionService;
    @Autowired
    TableUtils tableUtils;

    @Autowired
    UnitService unitService;

    @Autowired
    RoadwayService roadwayService;

    @Autowired
    IntersectionTypeService intersectionTypeService;

    @Autowired
    IntersectRecordService intersectRecordService;

    @Autowired
    MapUtils mapUtils;

    @RequestMapping("/list")
    public String list(Model model) {
        Account account = getCurrentUser();
        String roleName = account.getUser().getRole().getRoleName();
        List<Intersection> intersectionList = new ArrayList<>();
        List<Unit> unitList = new ArrayList<>();
        if (roleName.equals("ADMIN")) {
            intersectionList = intersectionService.findList("", 0, 1);
            unitList = unitService.findLevelOne(0);
            unitList.add(0, new Unit());
        } else if (roleName.equals("CAPTAIN")) {
            Unit adminUnit = account.getUser().getAdminunit();
            if (!"".equals(adminUnit)) {
                intersectionList = intersectionService.findList("", adminUnit.getUnitId(), 1);
            }
        }
        List<Intersection> currentList = tableUtils.getList(1, 7, intersectionList);
        Map<String, Integer> pageList = tableUtils.getPageList(1, 7, intersectionList);
        model.addAttribute("unitList", unitList);
        model.addAttribute("currentList", JsonUtils.toJson(currentList));
        model.addAttribute("pageList", JsonUtils.toJson(pageList));
        return "admin/intersection/intersectionList";
    }

    @RequestMapping("/page")
    @ResponseBody
    public String page(@Param("nameField") String nameField, @Param("unitId") int unitId, @Param("pageNumber") int pageNumber,
                       @Param("sortStatus") int sortStatus) {
        Account account = getCurrentUser();
        String roleName = account.getUser().getRole().getRoleName();
        List<Intersection> intersectionList = new ArrayList<>();
        Map<String, Object> map = new HashedMap();
        if (unitId == 0) {
            if (roleName.equals("CAPTAIN")) {
                intersectionList = intersectionService.findList(nameField, account.getUser().getAdminunit().getUnitId(), sortStatus);
            } else {
                intersectionList = intersectionService.findList(nameField, 0, sortStatus);
            }
        } else {
            intersectionList = intersectionService.findList(nameField, unitId, sortStatus);
        }
        List<Intersection> currentList = tableUtils.getList(pageNumber, 7, intersectionList);
        Map<String, Integer> pageList = tableUtils.getPageList(pageNumber,  7,intersectionList);
        map.put("currentList", currentList);
        map.put("currentPages", pageList);
        return JsonUtils.toJson(map);
    }

    @RequestMapping("/intersectionPage")
    public String page(@Param("intersectionId") int intersectionId, Model model) {
        Intersection intersection = new Intersection();
//        List
        if (intersectionId != 0) {
            intersection = intersectionService.findById(intersectionId);
        }
        List<RoadwayDropList> roadways = roadwayService.findAll();
        List<IntersectionType> intersectionTypes = intersectionTypeService.findAll();
        List<Unit> units = unitService.findLevelOne(0);
        List<Integer> records = intersectRecordService.findRecords(intersectionId);
        model.addAttribute("units", units);
        model.addAttribute("intersectionTypes", intersectionTypes);
        model.addAttribute("intersection", intersection);
        model.addAttribute("roadways", JsonUtils.toJson(roadways));
        model.addAttribute("records", JsonUtils.toJson(records));
        return "/admin/intersection/intersectionPage";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Intersection intersection) {
        Map<String, Object> results = new HashedMap();
        if(intersection.getIntersectionId() == null){
            if(intersectionService.findByName(intersection.getIntersectionName()) != null){
                results = ajaxDoneHint("路口名称已存在");
                return JsonUtils.toJson(results);
            }
        }else{
            if(intersectionService.findByName(intersection.getIntersectionName())!=null){
                if(!intersectionService.findByName(intersection.getIntersectionName()).getIntersectionName().equals(intersection.getIntersectionName())){
                    results = ajaxDoneHint("路口名称已存在");
                    return JsonUtils.toJson(results);
                }
            }
        }
        try{
            intersection.setUser(getCurrentUser().getUser());
            intersectionService.save(intersection);
            int id = intersectionService.findByName(intersection.getIntersectionName()).getIntersectionId();
            intersectRecordService.add(intersection.getRoadwayIds(), id);
            results = ajaxDoneSuccess("保存成功");
            return JsonUtils.toJson(results);
        }catch (Exception e){
            e.printStackTrace();
            results = ajaxDoneError("服务其内部错误，请与管理员联系");
            return JsonUtils.toJson(results);
        }
    }

    @RequestMapping("/mapDetail")
    public String mapDetail(Model model) {
//        model.addAttribute("mapMarkers", JsonUtils.toJson(intersectionService.findMapInfo()));
        return "admin/map/map";
    }
}
