package com.kunge2013.spring.transaction.mapper;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/5/24 20:00
 * @Version 1.0
 */

import com.kunge2013.spring.transaction.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO tb_user(username, age) VALUES(#{user.username}, #{user.age})")
    int insert(@Param("user") User user);
}