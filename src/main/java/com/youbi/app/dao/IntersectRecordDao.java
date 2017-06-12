package com.youbi.app.dao;

import com.youbi.app.model.IntersectRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntersectRecordDao {

    int selectByIntersectionId(Integer intersectionId);

    int deleteByPrimaryKey(IntersectRecord key);

    int delete(Integer intersectionId);

    List<Integer> selectRecords(Integer intersectionId);

    int insertSelective(@Param("intersectionId") Integer intersectionId, @Param("roadId") Integer roadId);


}