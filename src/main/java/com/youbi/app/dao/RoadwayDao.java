package com.youbi.app.dao;

import com.youbi.app.core.vo.RoadwayDropList;
import com.youbi.app.model.Roadway;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadwayDao {

    List<RoadwayDropList> selectList(String roadName);

    List<RoadwayDropList> selectAll();

    int deleteByPrimaryKey(Integer roadId);

    int insert(Roadway record);

    int insertSelective(Roadway record);

    Roadway selectByPrimaryKey(Integer roadId);

    Roadway selectByName(String roadName);

    int updateByPrimaryKeySelective(Roadway record);

    int updateByPrimaryKey(Roadway record);

    List<Roadway> selectByIntersectionId(int intersectionId);

    List<Roadway> selectDropList();
}