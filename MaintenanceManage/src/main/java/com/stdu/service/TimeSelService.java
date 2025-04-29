package com.stdu.service;

import com.stdu.mapper.TimeSelMapper;
import com.stdu.pojo.TimeSel;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TimeSelService {

    // 使用工具类获取SqlSessionFactory（单例模式）
    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 初始化建立自动生成时间表
    public void init(TimeSel timeSel) {
        try (SqlSession session = factory.openSession()) {
            TimeSelMapper timeSelMapper = session.getMapper(TimeSelMapper.class);
            timeSelMapper.init(timeSel);
            session.commit(); // 提交事务
        }
    }

    //根据工具类型删除自动生成时间表
    public int deleteByOrderType(int orderType) {
        try (SqlSession session = factory.openSession()) {
            TimeSelMapper timeSelMapper = session.getMapper(TimeSelMapper.class);
            timeSelMapper.deleteByOrderType(orderType);
            session.commit();
            return 1;
        }
    }

    // 更新对应工单的自动生成日期
    public void update(TimeSel timeSel) {
        try (SqlSession session = factory.openSession()) {
            TimeSelMapper timeSelMapper = session.getMapper(TimeSelMapper.class);
            timeSelMapper.update(timeSel);
            session.commit();
        }
    }
    //获取所有的自动生成日期
    public List<TimeSel> selectAll() {
        try (SqlSession session = factory.openSession()) {
            TimeSelMapper timeSelMapper = session.getMapper(TimeSelMapper.class);
            return timeSelMapper.selectAll();
        }
    }

    public TimeSel selectByOrderType(String orderType) {
        try (SqlSession session = factory.openSession()) {
            TimeSelMapper timeSelMapper = session.getMapper(TimeSelMapper.class);
            return timeSelMapper.selectByOrderType(orderType);
        }
    }

    public TimeSel selectByOrderTypeAndTemplateOrderId(String orderType,String templateOrderId) {
        try (SqlSession session = factory.openSession()) {
            TimeSelMapper timeSelMapper = session.getMapper(TimeSelMapper.class);
            return timeSelMapper.selectByOrderTypeAndTemplateOrderId(orderType,templateOrderId);
        }
    }
}
