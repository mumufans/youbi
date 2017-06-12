package com.youbi.app.dao;

import com.youbi.app.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    List<User> selectList (@Param("unitId")int unitId, @Param("userName")String userName,
                           @Param("orderName") int orderName, @Param("orderStatus") int orderStatus,
                           @Param("start") int start, @Param("pageSize") int pageSize);

    int insert(User record);

    List<User> selectAuthList(@Param("unitId")int unitId, @Param("userName")String userName,
                              @Param("orderName") int orderName, @Param("orderStatus") int orderStatus,
                              @Param("start") int start, @Param("pageSize") int pageSize);

    User selectByUserName(String userName);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int selectCount(@Param("unitId") int unitId, @Param("userName") String userName);

}