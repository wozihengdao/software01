package com.stdu.service;

import com.stdu.mapper.SpareOrderMapper;
import com.stdu.pojo.SpareOrder;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class SpareOrderService {
    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 添加备件单
    public void addSpareOrder(SpareOrder order) {
        try (SqlSession session = factory.openSession()) {
            SpareOrderMapper mapper = session.getMapper(SpareOrderMapper.class);
            mapper.insert(order);
            session.commit();
        }
    }

    // 删除备件单
    public void deleteById(Long id) {
        try (SqlSession session = factory.openSession()) {
            SpareOrderMapper mapper = session.getMapper(SpareOrderMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 更新备件单
    public void updateSpareOrder(SpareOrder order) {
        try (SqlSession session = factory.openSession()) {
            SpareOrderMapper mapper = session.getMapper(SpareOrderMapper.class);
            mapper.update(order);
            session.commit();
        }
    }

    // 查询所有备件单
    public List<SpareOrder> selectAll() {
        try (SqlSession session = factory.openSession()) {
            SpareOrderMapper mapper = session.getMapper(SpareOrderMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据ID查询
    public SpareOrder selectById(Long id) {
        try (SqlSession session = factory.openSession()) {
            SpareOrderMapper mapper = session.getMapper(SpareOrderMapper.class);
            return mapper.selectById(id);
        }
    }
}