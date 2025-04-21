package com.stdu.service;

import com.stdu.mapper.RepairOrderMapper;
import com.stdu.pojo.RepairOrder;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class RepairOrderService {
    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 查询所有报修单
    public List<RepairOrder> selectAll() {
        try (SqlSession session = factory.openSession()) {
            RepairOrderMapper mapper = session.getMapper(RepairOrderMapper.class);
            return mapper.findAll();
        }  // try-with-resources 自动关闭session
    }

    // 新增报修单（ID自增）
    public void addRepairOrder(RepairOrder order) {
        try (SqlSession session = factory.openSession()) {
            RepairOrderMapper mapper = session.getMapper(RepairOrderMapper.class);
            mapper.insert(order);
            session.commit();
        }
    }

    // 根据ID删除报修单
    public void deleteById(Long id) {
        try (SqlSession session = factory.openSession()) {
            RepairOrderMapper mapper = session.getMapper(RepairOrderMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 修改报修单
    public void updateRepairOrder(RepairOrder order) {
        try (SqlSession session = factory.openSession()) {
            RepairOrderMapper mapper = session.getMapper(RepairOrderMapper.class);
            mapper.update(order);
            session.commit();
        }
    }

    // 根据ID查询单个报修单（扩展方法）
    public RepairOrder selectById(Long id) {
        try (SqlSession session = factory.openSession()) {
            RepairOrderMapper mapper = session.getMapper(RepairOrderMapper.class);
            return mapper.findById(id);
        }
    }

    public RepairOrder selectLast() {

        try (SqlSession session = factory.openSession()) {
            RepairOrderMapper mapper = session.getMapper(RepairOrderMapper.class);
            return mapper.selectLast();
        }

    }

}