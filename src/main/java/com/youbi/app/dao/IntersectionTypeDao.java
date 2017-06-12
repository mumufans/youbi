package com.youbi.app.dao;

import com.youbi.app.model.IntersectionType;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IntersectionTypeDao {
    List<IntersectionType> selectAll();

    int deleteByPrimaryKey(Integer intersectionTypeId);

    int insert(IntersectionType record);

    int insertSelective(IntersectionType record);

    IntersectionType selectType(Integer intersectionTypeId);

    int updateByPrimaryKeySelective(IntersectionType record);

    int updateByPrimaryKey(IntersectionType record);
}