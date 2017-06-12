package com.youbi.app.service;

import com.youbi.app.core.vo.RoadwayDropList;
import com.youbi.app.dao.RoadwayDao;
import com.youbi.app.model.Roadway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hubin1 on 2017/4/12.
 */

@Service
public class RoadwayService {

    @Autowired
    RoadwayDao roadwayDao;

    public boolean save(Roadway roadway) {
        if (roadway.getRoadId() != null) {
            if(roadwayDao.selectByName(roadway.getRoadName()) != null){
                if(roadway.getRoadId().equals(roadwayDao.selectByName(roadway.getRoadName()).getRoadId())){
                    roadwayDao.updateByPrimaryKeySelective(roadway);
                    return true;
                }else {
                    return false;
                }
            }else{
                roadwayDao.updateByPrimaryKeySelective(roadway);
                return true;
            }
        } else {
            if (roadwayDao.selectByName(roadway.getRoadName()) == null) {
                roadwayDao.insertSelective(roadway);
                return true;
            } else {
                return false;
            }
        }
    }

    public Roadway findById(int id){
        return roadwayDao.selectByPrimaryKey(id);
    }

    public List<RoadwayDropList> findAll() {
        return roadwayDao.selectAll();
    }

    public List<RoadwayDropList> findList(String roadName) {
        return roadwayDao.selectList(roadName);
    }

    public List<Roadway> findDropList(){
        return roadwayDao.selectDropList();
    }

}
