package com.stdu.service;

import com.stdu.mapper.EquipmentMapper;
import com.stdu.pojo.Equipment;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class EquipmentService {

    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 添加设备
    public void addEquipment(Equipment equipment) {
        try (SqlSession session = factory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            mapper.insert(equipment);
            session.commit();
        }
    }

    // 删除设备
    public void deleteById(Long id) {
        try (SqlSession session = factory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 更新设备信息
    public void updateEquipment(Equipment equipment) {
        try (SqlSession session = factory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            mapper.update(equipment);
            session.commit();
        }
    }

    // 查询所有设备
    public List<Equipment> selectAll() {
        try (SqlSession session = factory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            return mapper.selectAll();
        }
    }

    // 根据ID查询设备
    public Equipment selectById(Long id) {
        try (SqlSession session = factory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            return mapper.selectById(id);
        }
    }
}
