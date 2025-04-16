package com.stdu.service;

import com.stdu.mapper.MaintenanceOrderMapper;
import com.stdu.pojo.MaintenanceOrder;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MaintenanceOrderService {
    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 添加维修单
    public void addOrder(MaintenanceOrder order) {
        try (SqlSession session = factory.openSession()) {
            MaintenanceOrderMapper mapper = session.getMapper(MaintenanceOrderMapper.class);
            mapper.insert(order);
            session.commit();
        }
    }

    // 删除维修单
    public void deleteById(Long id) {
        try (SqlSession session = factory.openSession()) {
            MaintenanceOrderMapper mapper = session.getMapper(MaintenanceOrderMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 更新维修单
    public void updateOrder(MaintenanceOrder order) {
        try (SqlSession session = factory.openSession()) {
            MaintenanceOrderMapper mapper = session.getMapper(MaintenanceOrderMapper.class);
            mapper.update(order);
            session.commit();
        }
    }

    // 查询所有维修单
    public List<MaintenanceOrder> selectAll() {
        try (SqlSession session = factory.openSession()) {
            MaintenanceOrderMapper mapper = session.getMapper(MaintenanceOrderMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据ID查询
    public MaintenanceOrder selectById(Long id) {
        try (SqlSession session = factory.openSession()) {
            MaintenanceOrderMapper mapper = session.getMapper(MaintenanceOrderMapper.class);
            return mapper.selectById(id);
        }
    }
}