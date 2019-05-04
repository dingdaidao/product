package com.ding.demo2;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {
    @Select("select username from user where username=#{username} and password=#{password}")
    public String login(@Param("username") String userName, @Param("password") String password);

}
