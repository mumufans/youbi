package com.youbi.app.core.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.youbi.app.core.security.Account;
import com.youbi.app.core.utils.JsonUtils;
import com.youbi.app.core.utils.TableUtils;
import com.youbi.app.model.Unit;
import com.youbi.app.model.UnitElm;
import com.youbi.app.model.User;
import com.youbi.app.service.CountyService;
import com.youbi.app.service.UnitService;
import com.youbi.app.service.UserService;
import com.youbi.app.validator.UnitValidator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hubin1 on 2017/3/16.
 */

@Controller
@RequestMapping("/unit")
public class UnitController extends BaseController {

    public static Logger logger = Logger.getLogger(UnitController.class);

    @Autowired
    UnitService unitService;
    @Autowired
    UnitValidator unitValidator;
    @Autowired
    TableUtils tableUtils;
    @Autowired
    UserService userService;
    @Autowired
    CountyService countyService;

    //进入单位列表
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public String getUnitList() {
        return "admin/unit/unitList";
    }

    //单位列表排序，点击页码
    @RequestMapping(value = "/page", method = {RequestMethod.GET})
    @ResponseBody
    public String page(HttpServletRequest request) {
        int sort = Integer.parseInt(request.getParameter("sort"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int start = getStart(pageNumber);
        List<Unit> unitList = unitService.findAll(sort, start, pageSize);
        Map<String, Integer> currentPages = getPageList(pageNumber, unitService.findCount());
        Map<String, Object> map = new HashMap<>();
        map.put("currentList", unitList);
        map.put("currentPages", currentPages);
        return JsonUtils.toJson(map);
    }

    //进入添加/修改页面
    @RequestMapping(value = "/unitPage", method = {RequestMethod.GET})
    public String editPage(@Param("unitId") int unitId, Model model) {
        Unit unit = new Unit();
        if (unitId != 0) {
            unit = unitService.findById(unitId);
        }
        model.addAttribute("countyList", countyService.findDropList());
        model.addAttribute("unit", unit);
        model.addAttribute("LevelOneList", unitService.findLevelOne(0));
        return "admin/unit/unitPage";
    }

    //添加/修改单位
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public String save(UnitElm unitElm, BindingResult result) {
        Map<String, Object> results = new HashMap<>();
        if (unitElm.getParent() == null) {
            unitElm.setUnitLevel(1);
        } else {
            unitElm.setUnitLevel(2);
        }
        unitValidator.validate(unitElm, result);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            String message = "";
            for (int i = 0; i < errors.size(); i++) {
                message = message + errors.get(i).getDefaultMessage() + "\n";
            }
            results = ajaxDoneHint(message);
        } else {
            try {
                unitService.save(unitElm);
            } catch (Exception e) {
                e.printStackTrace();
                results = ajaxDoneError("服务器内部错误");
                return JsonUtils.toJson(results);
            }
            results = ajaxDoneSuccess("添加成功");
        }
        return JsonUtils.toJson(results);
    }

    //获取二级单位下拉框内容
    @RequestMapping(value = "/getNextLevel", method = {RequestMethod.GET})
    @ResponseBody
    public String getNextLevel(@Param("unitId") int unitId) {
        Map<String, Object> map = new HashedMap();
        System.out.println(unitService.findNextLevel(unitId).size());
        map.put("unitNextLevel", unitService.findNextLevel(unitId));
        return JsonUtils.toJson(map);
    }

//    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
//    @ResponseBody
//    public String delete(Integer unitId){
//        List<User> userList = userService.findList(unitId, "", 1, 1);
//        List<Unit> unitList = unitService.findNextLevel(unitId);
//        if(unitList.size() != 0){
//            return JsonUtils.toJson(ajaxDoneError("此单位可能存在下属单位,请先删除下属单位和下属用户"));
//        }
//        if(userList.size() == 0){
//            try{unitService.deleteUnit(unitId);}catch (Exception e){
//                e.printStackTrace();
//                return JsonUtils.toJson(ajaxDoneError("此单位可能存在下属单位,请先删除下属单位和下属用户"));
//            }
//            return JsonUtils.toJson(ajaxDoneSuccess("删除成功!"));
//        }else{
//            return JsonUtils.toJson(ajaxDoneError("此单位可能存在下属人员,请先删除下属人员"));
//        }
//    }

    @RequestMapping(value = "/getDropList", method = {RequestMethod.GET})
    @ResponseBody
    public String getDropList() {
        List<Unit> levelOne = unitService.findLevelOne(0);
        levelOne.add(0, new Unit());
        Map<String, Object> results = new HashMap<>();
        results.put("levelOne", levelOne);
        return JsonUtils.toJson(results);
//        List<Unit> levelOne = new ArrayList<>();
//        List<Unit> levelTwo = new ArrayList<>();
//        User currentUser = getCurrentUser().getUser();
//        String roleName = currentUser.getRole().getRoleName();
//        Unit empty = new Unit();
//        if(roleName.equals("ADMIN")){
//            levelOne = unitService.findLevelOne(0);
//            levelOne.add(0,empty);
//        }else if(roleName.equals("CAPTAIN")){
//            if(currentUser.getAdminunit().getUnitLevel() == 1){
//                levelOne = unitService.findLevelOne(currentUser.getAdminunit().getUnitId());
//                levelTwo = unitService.findNextLevel(currentUser.getAdminunit().getUnitId());
//                levelTwo.add(0, empty);
//            }else{
//                levelOne.add(unitService.findById(currentUser.getAdminunit().getParent().getUnitId()));
//                levelTwo.add(currentUser.getAdminunit());
//            }
//        }
//        Map<String, Object> results = new HashMap<>();
//        results.put("levelOne", levelOne);
//        results.put("levelTwo", levelTwo);
//        return JsonUtils.toJson(results);
    }
}


