package com.stdu.service;

import com.stdu.mapper.CheckOrderMapper;
import com.stdu.pojo.CheckOrder;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class CheckOrderService {

    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 创建检修单
    public void createCheckOrder(CheckOrder order) {
        try (SqlSession session = factory.openSession()) {
            CheckOrderMapper mapper = session.getMapper(CheckOrderMapper.class);
            mapper.insert(order);
            session.commit();
        }
    }

    // 删除检修单
    public void deleteCheckOrder(Long id) {
        try (SqlSession session = factory.openSession()) {
            CheckOrderMapper mapper = session.getMapper(CheckOrderMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 更新检修单
    public void updateCheckOrder(CheckOrder order) {
        try (SqlSession session = factory.openSession()) {
            CheckOrderMapper mapper = session.getMapper(CheckOrderMapper.class);
            mapper.update(order);
            session.commit();
        }
    }

    // 获取所有检修单
    public List<CheckOrder> getAllCheckOrders() {
        try (SqlSession session = factory.openSession()) {
            CheckOrderMapper mapper = session.getMapper(CheckOrderMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据ID查询检修单
    public CheckOrder getCheckOrderById(Long id) {
        try (SqlSession session = factory.openSession()) {
            CheckOrderMapper mapper = session.getMapper(CheckOrderMapper.class);
            return mapper.selectById(id);
        }
    }

    // 可选：带条件的分页查询（根据需求扩展）

}