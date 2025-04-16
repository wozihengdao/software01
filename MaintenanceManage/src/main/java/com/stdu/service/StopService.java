package com.stdu.service;

import com.stdu.mapper.StopMapper;
import com.stdu.pojo.Stop;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StopService {

    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 添加车站
    public void addStop(Stop stop) {
        try (SqlSession session = factory.openSession()) {
            StopMapper mapper = session.getMapper(StopMapper.class);
            mapper.insert(stop);
            session.commit();
        }
    }

    // 删除车站
    public void deleteStop(Long id) {
        try (SqlSession session = factory.openSession()) {
            StopMapper mapper = session.getMapper(StopMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 更新车站信息
    public void updateStop(Stop stop) {
        try (SqlSession session = factory.openSession()) {
            StopMapper mapper = session.getMapper(StopMapper.class);
            mapper.update(stop);
            session.commit();
        }
    }

    // 查询所有车站
    public List<Stop> selectAllStops() {
        try (SqlSession session = factory.openSession()) {
            StopMapper mapper = session.getMapper(StopMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据ID查询车站
    public Stop selectStopById(Long id) {
        try (SqlSession session = factory.openSession()) {
            StopMapper mapper = session.getMapper(StopMapper.class);
            return mapper.selectById(id);
        }
    }

}