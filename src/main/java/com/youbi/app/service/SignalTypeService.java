package com.youbi.app.service;

import com.youbi.app.dao.SignalTypeDao;
import com.youbi.app.model.SignalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hubin1 on 2017/4/17.
 */

@Service
public class SignalTypeService {

    @Autowired
    SignalTypeDao signalTypeDao;

    public List<SignalType> findAll(){
        return signalTypeDao.selectAll();
    }
}
