package com.stdu.service;

import com.stdu.mapper.UserMapper;
import com.stdu.pojo.User;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {

    // 使用工具类获取SqlSessionFactory（单例模式）
    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 添加用户
    public void addUser(User user) {
        try (SqlSession session = factory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.addUser(user);
            session.commit(); // 提交事务
        }
    }



    // 更新用户信息
    public void updateUser(User user) {
        try (SqlSession session = factory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.changeUser(user);
            session.commit();
        }
    }

    // 查询所有用户
    public List<User> selectAllUsers() {
        try (SqlSession session = factory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据用户名查询用户
    public User selectUserByUsername(String username) {
        try (SqlSession session = factory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.selectByUsername(username);
        }
    }

}
