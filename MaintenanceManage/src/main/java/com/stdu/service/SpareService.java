// SpareService.java
package com.stdu.service;

import com.stdu.mapper.SpareMapper;
import com.stdu.pojo.Spare;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class SpareService {

    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 添加备件
    public void addSpare(Spare spare) {
        try (SqlSession session = factory.openSession()) {
            SpareMapper mapper = session.getMapper(SpareMapper.class);
            mapper.insert(spare);
            session.commit();
        }
    }

    // 删除备件
    public void deleteSpare(Long id) {
        try (SqlSession session = factory.openSession()) {
            SpareMapper mapper = session.getMapper(SpareMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 更新备件信息
    public void updateSpare(Spare spare) {
        try (SqlSession session = factory.openSession()) {
            SpareMapper mapper = session.getMapper(SpareMapper.class);
            mapper.update(spare);
            session.commit();
        }
    }

    // 查询所有备件
    public List<Spare> selectAllSpares() {
        try (SqlSession session = factory.openSession()) {
            SpareMapper mapper = session.getMapper(SpareMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据ID查询备件
    public Spare selectSpareById(Long id) {
        try (SqlSession session = factory.openSession()) {
            SpareMapper mapper = session.getMapper(SpareMapper.class);
            return mapper.selectById(id);
        }
    }
}