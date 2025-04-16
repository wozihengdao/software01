package com.stdu.service;

import com.stdu.mapper.PatrolOrderMapper;
import com.stdu.pojo.PatrolOrder;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class PatrolOrderService {
    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 添加巡检单
    public void addPatrolOrder(PatrolOrder order) {
        try (SqlSession session = factory.openSession()) {
            PatrolOrderMapper mapper = session.getMapper(PatrolOrderMapper.class);
            mapper.insert(order);
            session.commit();
        }
    }

    // 删除巡检单
    public void deleteById(Long id) {
        try (SqlSession session = factory.openSession()) {
            PatrolOrderMapper mapper = session.getMapper(PatrolOrderMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 更新巡检单
    public void updatePatrolOrder(PatrolOrder order) {
        try (SqlSession session = factory.openSession()) {
            PatrolOrderMapper mapper = session.getMapper(PatrolOrderMapper.class);
            mapper.update(order);
            session.commit();
        }
    }

    // 查询所有巡检单
    public List<PatrolOrder> selectAll() {
        try (SqlSession session = factory.openSession()) {
            PatrolOrderMapper mapper = session.getMapper(PatrolOrderMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据ID查询
    public PatrolOrder selectById(Long id) {
        try (SqlSession session = factory.openSession()) {
            PatrolOrderMapper mapper = session.getMapper(PatrolOrderMapper.class);
            return mapper.selectById(id);
        }
    }
}