package com.youbi.app.service;

import com.youbi.app.dao.IntersectionDao;
import com.youbi.app.model.Intersection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntersectionService {

    @Autowired
    IntersectionDao intersectionDao;

    public List<Intersection> findList(String intersectionName, int unitId, int sortStatus){
        return intersectionDao.selectList(intersectionName, unitId, sortStatus);
    }

    public Intersection findById(int intersectionId){
        return intersectionDao.selectByPrimaryKey(intersectionId);
    }

    public void save(Intersection intersection){
        if(intersection.getIntersectionId() == null){
            intersection.setIsDamaged(false);
            intersectionDao.insertSelective(intersection);
        }else{
            intersectionDao.updateByPrimaryKeySelective(intersection);
        }
    }

    public Intersection findByName(String intersectionName){
        return intersectionDao.selectByName(intersectionName);
    }

//    public List<MapMarker> findMapInfo(){
//        return intersectionDao.selectMapInfo();
//    }
}
