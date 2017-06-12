package com.youbi.app.core.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ASUS on 2017/3/21.
 */
@Controller
public class LoginController {
    public static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginPage(){
        return "app/login";
    }
}
