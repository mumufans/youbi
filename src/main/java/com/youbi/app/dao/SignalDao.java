package com.youbi.app.dao;

import com.youbi.app.core.vo.Marker;
import com.youbi.app.model.Signal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SignalDao {

    List<Signal> selectList(@Param("signalTypeId") int signalTypeId, @Param("countyId") int countyId,
                            @Param("unitId") int unitId, @Param("roadId") int roadId, @Param("beginTime") Date beginTime,
                            @Param("endTime") Date endTime, @Param("isDamaged") int isDamaged,
                            @Param("isUsed") int isUsed, @Param("isConfirmed") int isConfirmed, @Param("orderStatus") boolean orderStatus,
                            @Param("start") int start, @Param("size") int size);

    int deleteByPrimaryKey(Integer signalId);

    int insertSelective(Signal record);

    Signal selectByPrimaryKey(Integer signalId);

    int updateByPrimaryKeySelective(Signal record);

    int selectCount(@Param("signalTypeId") int signalTypeId, @Param("countyId") int countyId,
                    @Param("unitId") int unitId, @Param("roadId") int roadId, @Param("beginTime") Date beginTime,
                    @Param("endTime") Date endTime, @Param("isDamaged") int isDamaged,
                    @Param("isUsed") int isUsed, @Param("isConfirmed") int isConfirmed);

    List<Marker> selectMarkers(@Param("signalTypeId") int signalTypeId, @Param("countyId") int countyId,
                               @Param("unitId") int unitId, @Param("roadId") int roadId,
                               @Param("beginTime") Date beginTime,
                               @Param("endTime") Date endTime, @Param("isDamaged") int isDamaged,
                               @Param("isUsed") int isUsed, @Param("isConfirmed") int isConfirmed);
}