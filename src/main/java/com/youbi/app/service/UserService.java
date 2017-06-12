package com.youbi.app.service;

import com.youbi.app.core.utils.AuthenticationUtils;
import com.youbi.app.dao.UserDao;
import com.youbi.app.model.User;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.util.List;

/**
 * Created by hubin1 on 2017/3/20.
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> findList(int unitId, String  userName, int orderName, int orderStatus, int start, int pageSize){
        return userDao.selectList(unitId, userName, orderName, orderStatus, start, pageSize );
    }

    public List<User> findAuthList(int unitId, String  userName, int orderName, int orderStatus, int start, int pageSize){
        return userDao.selectAuthList(unitId, userName, orderName, orderStatus, start, pageSize);
    }

    public User findByUserName(String userName){
        return userDao.selectByUserName(userName);
    }

    public void manageAdd(User user){
        String salt = RandomStringUtils.randomAlphanumeric(6);
        String hash = AuthenticationUtils.generatePassword(user.getHash() + salt);
        user.setSalt(salt);
        user.setHash(hash);
        int a =userDao.insertSelective(user);
    }

    public int getCount(int unitId, String userName){
        return userDao.selectCount(unitId, userName);
    }

    public void update(User user){
        userDao.updateByPrimaryKeySelective(user);
    }

    public User findByUserId(int userId){
        return userDao.selectByPrimaryKey(userId);
    }

    public void delete(int userId){
        userDao.deleteByPrimaryKey(userId);
    }

    public void changePSW(int id, String password){
        String salt = RandomStringUtils.randomAlphanumeric(6);
        String hash = AuthenticationUtils.generatePassword(password + salt);
        User user = new User();
        user.setUserId(id);
        user.setSalt(salt);
        user.setHash(hash);
        userDao.updateByPrimaryKeySelective(user);
    }
}
