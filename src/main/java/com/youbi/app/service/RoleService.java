package com.youbi.app.service;

import com.youbi.app.dao.RoleDao;
import com.youbi.app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/3/31.
 */

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    public List<Role> findList(){
        return roleDao.selectList();
    }
}
