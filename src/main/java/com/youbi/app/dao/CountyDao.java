package com.youbi.app.dao;

import com.youbi.app.model.County;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountyDao {

    County selectByPrimaryKey(Integer id);

    List<County> selectDropList();

}