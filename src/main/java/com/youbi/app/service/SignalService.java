package com.youbi.app.service;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import com.youbi.app.dao.SignalDao;
import com.youbi.app.model.Signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/4/15.
 */
@Service
public class SignalService {

    @Autowired
    SignalDao signalDao;
    public List<Signal> findList(int signalTypeId, int countyId, int unitId, int roadId, Date beginTime, Date endTime,
                                 int isDamaged,int isUsed,int isConfirmed, boolean orderStatus, int start, int pageSize){
        return signalDao.selectList(signalTypeId,countyId,unitId,roadId,beginTime,endTime,isDamaged, isUsed,isConfirmed, orderStatus,
                start, pageSize);
    }

    public Signal findById(int signalId){
        return signalDao.selectByPrimaryKey(signalId);
    }

    public void save(Signal signal){
        if(signal.getSignalId() != null){
            signalDao.updateByPrimaryKeySelective(signal);
        }else {
            signalDao.insertSelective(signal);
        }
    }

    public int findCount(int signalTypeId, int countyId, int unitId, int roadId, Date beginTime,
                         Date endTime, int isDamaged, int isUsed, int isConfirmed){
        return signalDao.selectCount(signalTypeId, countyId,unitId, roadId, beginTime, endTime, isDamaged, isUsed, isConfirmed);
    }
}
