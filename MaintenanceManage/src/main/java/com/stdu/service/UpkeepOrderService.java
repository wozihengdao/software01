package com.stdu.service;

import com.stdu.mapper.UpkeepOrderMapper;
import com.stdu.pojo.UpkeepOrder;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class UpkeepOrderService {
    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 添加保养单
    public void addUpkeepOrder(UpkeepOrder order) {
        try (SqlSession session = factory.openSession()) {
            UpkeepOrderMapper mapper = session.getMapper(UpkeepOrderMapper.class);
            mapper.insert(order);
            session.commit();
        }
    }

    // 删除保养单
    public void deleteById(Long id) {
        try (SqlSession session = factory.openSession()) {
            UpkeepOrderMapper mapper = session.getMapper(UpkeepOrderMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 更新保养单
    public void updateUpkeepOrder(UpkeepOrder order) {
        try (SqlSession session = factory.openSession()) {
            UpkeepOrderMapper mapper = session.getMapper(UpkeepOrderMapper.class);
            mapper.update(order);
            session.commit();
        }
    }

    // 查询所有保养单
    public List<UpkeepOrder> selectAll() {
        try (SqlSession session = factory.openSession()) {
            UpkeepOrderMapper mapper = session.getMapper(UpkeepOrderMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据ID查询
    public UpkeepOrder selectById(Long id) {
        try (SqlSession session = factory.openSession()) {
            UpkeepOrderMapper mapper = session.getMapper(UpkeepOrderMapper.class);
            return mapper.selectById(id);
        }
    }
}