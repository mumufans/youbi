package com.youbi.app.dao;

import com.youbi.app.model.Unit;
import com.youbi.app.model.UnitElm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitDao {

    List<Unit> selectAll(@Param("sort") int sort, @Param("start") int start, @Param("pageSize")int pageSize);

    Unit selectByName(String unitName);

    int selectCount();

    Unit selectById(int unitId);

    List<Unit> selectLevelOne(@Param("adminUnitId") int adminUnitId);

    void insertSelective(UnitElm unit);

    void updateByPrimaryKeySelective(UnitElm unit);

    Unit selectByNumber(String unitNumber);

    List<Unit> selectNextLevel(int unitId);

    List<Unit> selectAdminUnit(@Param("adminUnitId") int unitId, @Param("sort") int sort,
                               @Param("start") int start, @Param("pageSize")int pageSize);

    void deleteByPrimaryKey(Integer unitId);
}