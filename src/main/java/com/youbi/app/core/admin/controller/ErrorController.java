package com.youbi.app.core.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hubin1 on 2017/3/17.
 */
@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String err(){
        return  "/error/error";
    }
}
