package com.youbi.app.service;

import com.youbi.app.dao.IntersectionTypeDao;
import com.youbi.app.model.IntersectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntersectionTypeService {

    @Autowired
    IntersectionTypeDao intersectionTypeDao;

    public List<IntersectionType> findAll(){
        return intersectionTypeDao.selectAll();
    }
}
