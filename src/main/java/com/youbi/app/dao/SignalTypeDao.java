package com.youbi.app.dao;

import com.youbi.app.model.SignalType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignalTypeDao {

    SignalType selectByPrimaryKey(Integer signalTypeId);

    List<SignalType> selectAll();

}