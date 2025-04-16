package com.stdu.service;

import com.stdu.mapper.EngineerMapper;
import com.stdu.pojo.Engineer;
import com.stdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class EngineerService {

    private final SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    // 查询所有工程师
    public List<Engineer> selectAll() {
        try (SqlSession session = factory.openSession()) {
            EngineerMapper mapper = session.getMapper(EngineerMapper.class);
            return mapper.selectAll();
        }
    }

    // 新增工程师（自动生成ID）
    public void addEngineer(Engineer engineer) {
        try (SqlSession session = factory.openSession()) {
            EngineerMapper mapper = session.getMapper(EngineerMapper.class);
            mapper.insert(engineer);
            session.commit();  // 手动提交事务
        }
    }

    // 根据ID删除工程师
    public void deleteById(Long id) {
        try (SqlSession session = factory.openSession()) {
            EngineerMapper mapper = session.getMapper(EngineerMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
    }

    // 修改工程师信息
    public void updateEngineer(Engineer engineer) {
        try (SqlSession session = factory.openSession()) {
            EngineerMapper mapper = session.getMapper(EngineerMapper.class);
            mapper.update(engineer);
            session.commit();
        }
    }

    // 根据ID查询单个工程师
    public Engineer selectById(Long id) {
        try (SqlSession session = factory.openSession()) {
            EngineerMapper mapper = session.getMapper(EngineerMapper.class);
            return mapper.selectById(id);
        }
    }

//    // 可选：带条件的分页查询（扩展示例）
//    public List<Engineer> selectByCondition(String state, String type) {
//        try (SqlSession session = factory.openSession()) {
//            EngineerMapper mapper = session.getMapper(EngineerMapper.class);
//            return mapper.selectByCondition(state, type);
//        }
//    }

}
