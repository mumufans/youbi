package com.youbi.app.core.admin.controller;

import com.youbi.app.core.utils.JsonUtils;
import com.youbi.app.core.utils.TableUtils;
import com.youbi.app.core.vo.RoadwayDropList;
import com.youbi.app.model.County;
import com.youbi.app.model.Roadway;
import com.youbi.app.service.CountyService;
import com.youbi.app.service.RoadwayService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/4/13.
 */

@Controller
@RequestMapping("/management/roadway")
public class RoadwayController extends BaseController {

    @Autowired
    RoadwayService roadwayService;

    @Autowired
    TableUtils tableUtils;

    @Autowired
    CountyService countyService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public String list(Model model) {
        List<RoadwayDropList> list = roadwayService.findList("");
        List<RoadwayDropList> currentList1 = tableUtils.getList(1, 7, list);
        List<RoadwayDropList> currentList2 = tableUtils.getList(2, 7, list);
        List<RoadwayDropList> currentList3 = tableUtils.getList(3, 7, list);
        Map<String, Integer> pageList = tableUtils.getPageList(1, 21, list);
        model.addAttribute("currentList1", JsonUtils.toJson(currentList1));
        model.addAttribute("currentList2", JsonUtils.toJson(currentList2));
        model.addAttribute("currentList3", JsonUtils.toJson(currentList3));
        model.addAttribute("pageList", JsonUtils.toJson(pageList));
        return "/admin/roadway/roadwayList";
    }

    @RequestMapping(value = "/page", method = {RequestMethod.GET})
    @ResponseBody
    public String page(@Param("nameField") String nameField, @Param("pageNumber") Integer pageNumber) {
        Map<String, Object> map = new HashedMap();
        List<RoadwayDropList> list = roadwayService.findList(nameField);
        List<RoadwayDropList> currentList1 = tableUtils.getList((pageNumber - 1) * 3 + 1, 7, list);
        List<RoadwayDropList> currentList2 = tableUtils.getList((pageNumber - 1) * 3 + 2, 7, list);
        List<RoadwayDropList> currentList3 = tableUtils.getList((pageNumber - 1) * 3 + 3, 7, list);
        Map<String, Integer> pageList = tableUtils.getPageList(pageNumber, 21, list);
        map.put("currentList1", currentList1);
        map.put("currentList2", currentList2);
        map.put("currentList3", currentList3);
        map.put("currentPages", pageList);
        return JsonUtils.toJson(map);
    }

    @RequestMapping(value = "/editPage", method = {RequestMethod.GET})
    public String editPage(@Param("id")int id, Model model){
        Roadway roadway = new Roadway();
        if(id != 0){
            roadway = roadwayService.findById(id);
        }
        model.addAttribute("roadway",roadway);
        model.addAttribute("countyList", countyService.findDropList());
        return "/admin/roadway/roadwayPage";
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public String save(Roadway roadway){
        Map<String, Object> results = new HashedMap();
        boolean isSuccess = roadwayService.save(roadway);
        if(isSuccess){
            results = ajaxDoneSuccess("保存成功");
        }else{
            results = ajaxDoneHint("道路名称已存在");
        }
        return JsonUtils.toJson(results);
    }
}
