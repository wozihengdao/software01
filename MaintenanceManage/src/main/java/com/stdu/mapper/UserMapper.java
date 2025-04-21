package com.stdu.mapper;

import com.stdu.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    // 定义结果映射（对应数据库字段与POJO属性）
    @Results(id = "userResultMap", value = {
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "type", column = "type")
    })
    @Select("SELECT * FROM tb_user")
    List<User> selectAll();

    // 根据用户名查询
    @Select("SELECT * FROM tb_user WHERE username = #{username}")
    @ResultMap("userResultMap")
    User selectByUsername(String username);

    // 插入用户
    @Insert("INSERT INTO tb_user VALUES (#{username}, #{password}, #{type})")
    void addUser(User user);

    // 根据用户名删除
    @Delete("DELETE FROM tb_user WHERE username = #{username}")
    void deleteUser(String username);  // 直接使用username作为参数更合理

    // 更新用户信息（根据用户名修改）
    @Update("UPDATE tb_user SET " +
            "password = #{password}, " +
            "type = #{type} " +
            "WHERE username = #{username}")
    void changeUser(User user);

}
