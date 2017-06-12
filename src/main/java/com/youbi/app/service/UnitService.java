package com.youbi.app.service;

import com.youbi.app.dao.UnitDao;
import com.youbi.app.model.Unit;
import com.youbi.app.model.UnitElm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hubin1 on 2017/3/16.
 */

@Service
public class UnitService {

    @Autowired
    UnitDao unitDao;

    public List<Unit> findAll(int sort, int start, int pageSize) {
        return unitDao.selectAll(sort, start, pageSize);
    }

    public Unit findByName(String unitName) {
        return unitDao.selectByName(unitName);
    }

    public Unit findById(int unitId) {
        return unitDao.selectById(unitId);
    }

    public int findCount(){
        return unitDao.selectCount();
    }

    public List<Unit> findLevelOne(int unitId) {
        int unitLevelOne;
        if(unitId < 1){
            return unitDao.selectLevelOne(unitId);
        }
        if(findById(unitId).getUnitLevel() == 1){
            unitLevelOne = unitId;
        }else{
            unitLevelOne = findById(unitId).getParent().getUnitId();
        }
        return unitDao.selectLevelOne(unitLevelOne);
    }

    public void save(UnitElm unit) {
        if (unit.getUnitId() == null) {
            unitDao.insertSelective(unit);
        } else {
            unitDao.updateByPrimaryKeySelective(unit);
        }
    }

    public Unit findByUnitNumber(String unitNumber) {
        return unitDao.selectByNumber(unitNumber);
    }

    public List<Unit> findAdminUnit(int unitId, int sort, int start, int pageSize){
        return unitDao.selectAdminUnit(unitId, sort, start, pageSize);
    }

    public List<Unit> findNextLevel(int unitId) {
        List<Unit> unitList = new ArrayList<>();
        if(unitId == 0){
            return unitList;
        }
        return unitDao.selectNextLevel(unitId);
    }

    public void deleteUnit(int unitId){
        unitDao.deleteByPrimaryKey(unitId);
    }
}
