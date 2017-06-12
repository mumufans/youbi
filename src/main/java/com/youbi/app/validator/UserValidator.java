package com.youbi.app.validator;

import com.youbi.app.model.User;
import com.youbi.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by hubin1 on 2017/3/23.
 */

@Component
public class UserValidator implements Validator {

    @Autowired UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", null, "用户名不能为空");

        User user = (User)target;
        System.out.println(user.getUserId());
        if(user.getUserId() != null){
            if(userService.findByUserName(user.getUserName()) != null){
                if(!userService.findByUserName(user.getUserName()).getUserId().equals(user.getUserId())){
                    errors.rejectValue("userName", null, "用户名已存在");
                }
            }
        }else{
            if(userService.findByUserName(user.getUserName()) != null){
                errors.rejectValue("userName", null, "用户名已存在");
            }
        }
    }
}
