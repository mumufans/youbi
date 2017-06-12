package com.youbi.app.validator;

import com.youbi.app.model.Unit;
import com.youbi.app.model.UnitElm;
import com.youbi.app.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by hubin1 on 2017/3/17.
 */

@Component
public class UnitValidator implements Validator {

    @Autowired
    UnitService unitService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Unit.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitName", null, "单位名称不能为空");

        UnitElm unit  = (UnitElm)target;
        if(unit.getUnitId() == null){
            if (unitService.findByName(unit.getUnitName())!= null) {
                errors.rejectValue("unitName", null, "单位名称已存在");
            }
        }else{
            if(unitService.findByName(unit.getUnitName()) !=null){
                if(!unitService.findByName(unit.getUnitName()).getUnitId().equals(unit.getUnitId())){
                    errors.rejectValue("unitName", null, "单位名称已存在");
                }
            }
        }
    }
}
