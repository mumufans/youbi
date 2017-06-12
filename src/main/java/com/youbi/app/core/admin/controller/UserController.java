package com.youbi.app.core.admin.controller;

import com.youbi.app.core.security.Account;
import com.youbi.app.core.utils.JsonUtils;
import com.youbi.app.core.utils.TableUtils;
import com.youbi.app.model.Role;
import com.youbi.app.model.Unit;
import com.youbi.app.model.User;
import com.youbi.app.service.RoleService;
import com.youbi.app.service.SignalService;
import com.youbi.app.service.UnitService;
import com.youbi.app.service.UserService;
import com.youbi.app.validator.UserValidator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.th.CalendarData_th;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hubin1 on 2017/3/20.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    public static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    TableUtils tableUtils;

    @Autowired
    UnitService unitService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    RoleService roleService;

    @Autowired
    SignalService signalService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public String list(Model model) {
        Account currentUser = getCurrentUser();
        String roleName = currentUser.getUser().getRole().getRoleName();
        Unit adminUnit = currentUser.getUser().getAdminunit();
        List<Unit> unitLevelOne = new ArrayList<>();
        if (roleName.equals("ADMIN")) {
            unitLevelOne = unitService.findLevelOne(0);
        } else if (roleName.equals("CAPTAIN")) {
            if (adminUnit != null && !adminUnit.equals("")) {

                unitLevelOne = unitService.findNextLevel(adminUnit.getUnitId());
            }
        }
        System.out.println(unitLevelOne.size());
        model.addAttribute("unitLevelOne", JsonUtils.toJson(unitLevelOne));
        return "admin/user/userList";
    }

    @RequestMapping(value = "/page", method = {RequestMethod.GET})
    @ResponseBody
    public String page(HttpServletRequest request) {
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int unitId = Integer.parseInt(request.getParameter("unitId"));
        String userName = request.getParameter("userName");
        int sortName = Integer.parseInt(request.getParameter("sortName"));
        int sortStatus = Integer.parseInt(request.getParameter("sortStatus"));
        Account currentUser = getCurrentUser();
        String roleName = currentUser.getUser().getRole().getRoleName();
        Unit adminUnit = currentUser.getUser().getAdminunit();
        List<User> userList = new ArrayList<User>();
        int start = getStart(pageNumber);
        Map<String, Integer> pageList = new HashedMap();
        if (roleName.equals("ADMIN")) {
            userList = userService.findList(unitId, userName, sortName, sortStatus, start, pageSize);
            pageList = getPageList(pageNumber, userService.getCount(unitId, userName));
        } else if (roleName.equals("CAPTAIN")) {
            if (unitId == 0) {
                if (adminUnit != null && !adminUnit.equals("")) {
                    userList = userService.findList(adminUnit.getUnitId(), userName, sortName, sortStatus, start, pageSize);
                    pageList = getPageList(pageNumber, userService.getCount(adminUnit.getUnitId(), userName));
                }
            } else {
                userList = userService.findList(unitId, userName, sortName, sortStatus, start, pageSize);
                pageList = getPageList(pageNumber, userService.getCount(unitId, userName));
            }
        }
        Map<String, Object> map = new HashedMap();
        map.put("currentList", userList);
        map.put("currentPages", pageList);
        return JsonUtils.toJson(map);
    }

    @RequestMapping(value = "/authList", method = {RequestMethod.GET})
    public String authList(Model model) {
        Account currentUser = getCurrentUser();
        String roleName = currentUser.getUser().getRole().getRoleName();
        Unit adminUnit = currentUser.getUser().getAdminunit();
        List<Unit> unitLevelOne = new ArrayList<Unit>();
        if (roleName.equals("ADMIN")) {
            unitLevelOne = unitService.findLevelOne(0);
        } else if (roleName.equals("CAPTAIN")) {
            if (adminUnit != null && !adminUnit.equals("")) {
                unitLevelOne = unitService.findNextLevel(adminUnit.getUnitId());
            }
        }
        model.addAttribute("unitLevelOne", JsonUtils.toJson(unitLevelOne));
        return "admin/user/authList";
    }

    @RequestMapping(value = "/authPage", method = {RequestMethod.GET})
    @ResponseBody
    public String authPage(@Param("userName") String userName, @Param("unitId") int unitId, @Param("sortName") int sortName,
                           @Param("sortStatus") int sortStatus, @Param("pageNumber") int pageNumber) {
        Account currentUser = getCurrentUser();
        String roleName = currentUser.getUser().getRole().getRoleName();
        Unit adminUnit = currentUser.getUser().getAdminunit();
        List<User> userList = new ArrayList<User>();
        int start = getStart(pageNumber);
        Map<String, Integer> pageList = new HashedMap();
        if (roleName.equals("ADMIN")) {
            userList = userService.findAuthList(unitId, userName, sortName, sortStatus, start, pageSize);
            pageList = getPageList(pageNumber, userService.getCount(unitId, userName));
        } else if (roleName.equals("CAPTAIN")) {
            if (unitId == 0) {
                if (adminUnit != null && !adminUnit.equals("")) {
                    userList = userService.findAuthList(adminUnit.getUnitId(), userName, sortName, sortStatus, start, pageSize);
                    pageList = getPageList(pageNumber, userService.getCount(adminUnit.getUnitId(), userName));
                }
            } else {
                userList = userService.findAuthList(unitId, userName, sortName, sortStatus, start, pageSize);
                pageList = getPageList(pageNumber, userService.getCount(unitId, userName));
            }
        }
        Map<String, Object> map = new HashedMap();
        map.put("currentList", userList);
        map.put("currentPages", pageList);
        return JsonUtils.toJson(map);
    }

    @RequestMapping(value = "/addUserPage", method = {RequestMethod.GET})
    public String addUserPage(@Param("userId") int userId, Model model) {
        Account currentUser = getCurrentUser();
        String roleName = currentUser.getUser().getRole().getRoleName();
        Unit adminUnit = currentUser.getUser().getAdminunit();
        User user = new User();
        Map<String, Integer> inject = new HashedMap();
        List<Unit> unitLevelOne = new ArrayList<Unit>();
        List<Unit> unitLevelTwo = new ArrayList<Unit>();
        Unit empty = new Unit();
        if (userId > 0) {
            user = userService.findByUserId(userId);
        }
        Unit userUnit = user.getUnit();
        if (roleName.equals("ADMIN")) {
            unitLevelOne = unitService.findLevelOne(0);
            unitLevelOne.add(0, empty);
            if (userUnit != null && !userUnit.equals("")) {
                int x = userUnit.getUnitId();
                if (userUnit.getUnitLevel() == 1) {
                    inject.put("unitOne", x);
                    unitLevelTwo = unitService.findNextLevel(x);
                    unitLevelTwo.add(0, empty);
                } else {
                    int y = unitService.findById(userUnit.getParent().getUnitId()).getUnitId();
                    inject.put("unitOne", y);
                    inject.put("unitTwo", userUnit.getUnitId());
                    unitLevelTwo = unitService.findNextLevel(y);
                    unitLevelTwo.add(0, empty);
                }
            }
        } else if (roleName.equals("CAPTAIN")) {
            if (adminUnit != null && !adminUnit.equals("")) {
                int z = adminUnit.getUnitId();
                if (adminUnit.getUnitLevel() == 1) {
                    unitLevelOne.add(adminUnit);
                    unitLevelTwo = unitService.findNextLevel(z);
                    unitLevelTwo.add(0, empty);
                    inject.put("unitOne", z);
                    if (userUnit != null && !userUnit.equals("")) {
                        if (userUnit.getUnitLevel() == 2) {
                            inject.put("unitTwo", userUnit.getUnitId());
                        }
                    }
                } else {
                    unitLevelOne.add(unitService.findById(adminUnit.getParent().getUnitId()));
                    unitLevelTwo.add(adminUnit);
                }
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("unitLevelOne", unitLevelOne);
        model.addAttribute("unitLevelTwo", unitLevelTwo);
        model.addAttribute("inject", JsonUtils.toJson(inject));
        return "admin/user/userPage";
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    @ResponseBody
    public String addUser(User user, Model model, BindingResult result, HttpServletRequest request) {
        Map<String, Object> results = new HashedMap();
        if (null == user.getRole()) {
            Role role = new Role();
            role.setRoleId(3);
            user.setRole(role);
        }
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            results = ajaxDoneHint(result.getAllErrors().get(0).getDefaultMessage());
            return JsonUtils.toJson(results);
        }
        try {
            if (user.getUserId() == null) {
                if (user.getHash() == null || user.getHash().equals("")) {
                    user.setHash("111111");
                }
                userService.manageAdd(user);
            } else {
                userService.update(user);
            }
        } catch (Exception e) {
            results = ajaxDoneError("服务器内部错误");
            e.printStackTrace();
            return JsonUtils.toJson(results);
        }
        results = ajaxDoneSuccess("操作成功");
        return JsonUtils.toJson(results);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseBody
    public String delete(@RequestParam Integer userId) {
        try {
            userService.delete(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.toJson(ajaxDoneError("删除用户失败!"));
        }
        return JsonUtils.toJson(ajaxDoneSuccess("删除用户成功!"));
    }

    @RequestMapping(value = "/changePSW", method = {RequestMethod.POST})
    @ResponseBody
    public String changePSW(@RequestParam Integer id, @RequestParam String password) {
        userService.changePSW(id, password);
        return JsonUtils.toJson(ajaxDoneSuccess("修改密码成功"));
    }

    @RequestMapping(value = "/editAuthPage", method = {RequestMethod.GET})
    public String editAuthPage(@Param("userId") int userId, Model model) {
        List<Unit> unitLevelOneList = new ArrayList<>();              //单位下拉框注入
        List<Role> roleList = roleService.findList();                           //角色下拉框注入
        User user = userService.findByUserId(userId);
        if (user.getRole().getRoleName().equals("CAPTAIN")) {
            unitLevelOneList = unitService.findLevelOne(0);
        }
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        model.addAttribute("unitList", unitLevelOneList);
        return "admin/user/editAuth";
    }
}
