package com.youbi.app.dao;

import com.youbi.app.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectList();

}