package com.youbi.app.core.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hubin1 on 2017/3/15.
 */

@Controller
@RequestMapping("")
public class MainController {

    Logger logger = Logger.getLogger(MainController.class);

//    @RequestMapping("/")
//    public String index(){
//        return "/admin/index";
//    }

    @RequestMapping("/")
    public String main(){
        return "admin/index";
    }
}
