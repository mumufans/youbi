package com.youbi.app.service;

import com.youbi.app.dao.IntersectRecordDao;
import com.youbi.app.dao.IntersectionDao;
import com.youbi.app.model.IntersectRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hubin1 on 2017/4/12.
 */


@Service
public class IntersectRecordService {

    @Autowired
    IntersectRecordDao intersectRecordDao;

    public void add(List<Integer> roadwayIds, int id){
        intersectRecordDao.delete(id);
        for(int i = 0; i < roadwayIds.size(); i++){
            intersectRecordDao.insertSelective(id, roadwayIds.get(i));
        }
    }

    public List<Integer> findRecords(int intersectionId){
        return intersectRecordDao.selectRecords(intersectionId);
    }
}
