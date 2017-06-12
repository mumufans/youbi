package com.youbi.app.service;

import com.youbi.app.dao.CountyDao;
import com.youbi.app.model.County;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hubin1 on 2017/4/14.
 */

@Service
public class CountyService {

    @Autowired
    CountyDao countyDao;

    public List<County> findDropList(){
        return countyDao.selectDropList();
    }

    public County findById(int id){
        return countyDao.selectByPrimaryKey(id);
    }
}
