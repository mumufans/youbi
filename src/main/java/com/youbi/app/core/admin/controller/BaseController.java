package com.youbi.app.core.admin.controller;


import com.youbi.app.core.security.Account;
import com.youbi.app.model.User;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hubin1 on 2017/3/17.
 */


public abstract class BaseController {

    private static Logger logger = Logger.getLogger(BaseController.class);

    public final static String CURRENT_USER_SESSION_KEY = "currentUser";

    public final static int pageSize = 7;

    public final static int pages = 10;

    @Autowired
    HttpServletRequest request;

    private Account currentUser = null;

    protected Account getCurrentUser(){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(obj instanceof Account){
            currentUser = (Account)obj;
        }else{
            currentUser = null;
        }
        return currentUser;
    }

    protected int getStart(int pageNumber){
        return (pageNumber - 1) * pageSize;
    }

    protected Map<String, Integer> getPageList(int pageNumber, int total){
        int size = (int)(Math.ceil(total / (double)pageSize));
        Map<String, Integer> result = new HashedMap();
        if(pageNumber > size || pageNumber < 1){
            result.put("begin", 0);
            return result;
        }
        if(pageNumber <= 4){
            result.put("begin", 1);
            result.put("end", pageSize > size? size: 7);
        }else if(pageNumber > 4){
            result.put("begin", (size > 7 ? ((size - pageNumber) < 4 ? (size - 7): (pageNumber - 4)): 0) + 1);
            result.put("end", size > 7 ? ((size - pageNumber) < 4 ? size: (pageNumber + 3)): size);
        }
        return result;
    }

    protected Map<String , Object> ajaxDone(int statusCode, String message, String forwardUrl){
        Map<String, Object> mav = new HashMap<>();
        mav.put("statusCode", statusCode);
        mav.put("message", message);
        mav.put("forwardUrl", forwardUrl);
        return mav;
    }

    protected Map<String, Object> ajaxDoneSuccess(String message){
        return ajaxDone(200, message, "");
    }

    protected Map<String, Object> ajaxDoneError(String message){
        return ajaxDone(400, message, "");
    }

    protected Map<String, Object> ajaxDoneHint(String message){
        return ajaxDone(300, message, "");
    }


}
