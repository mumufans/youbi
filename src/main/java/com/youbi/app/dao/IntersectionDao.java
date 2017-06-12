package com.youbi.app.dao;

import com.youbi.app.model.Intersection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntersectionDao {
    int deleteByPrimaryKey(Integer intersectionId);

    int insert(Intersection record);

    int insertSelective(Intersection record);

    Intersection selectByPrimaryKey(Integer intersectionId);

    int updateByPrimaryKeySelective(Intersection record);

    Intersection selectByName(String intersectionName);

    List<Intersection> selectList(@Param("intersectionName") String intersectionName,
                                  @Param("unitId") int unitId, @Param("sortStatus") int sortStatus);

//    List<MapMarker> selectMapInfo();
}