package com.stdu.mapper;

import com.stdu.pojo.Engineer;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EngineerMapper {

    // 插入（自动回填生成的主键）
    @Insert("INSERT INTO tb_engineer (order_num, state, type) " +
            "VALUES (#{orderNum}, #{state}, #{type},#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Engineer engineer);

    // 根据ID删除
    @Delete("DELETE FROM tb_engineer WHERE id = #{id}")
    int deleteById(Long id);

    // 更新所有字段
    @Update("UPDATE tb_engineer SET " +
            "order_num = #{orderNum}, " +
            "state = #{state}, " +
            "name = #{name}, " +
            "type = #{type} " +
            "WHERE id = #{id}")
    int update(Engineer engineer);

    // 根据ID查询（包含字段映射）
    @Select("SELECT * FROM tb_engineer WHERE id = #{id}")
    @Results(id = "engineerMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "orderNum", column = "order_num"),
            @Result(property = "state", column = "state"),
            @Result(property = "type", column = "type"),
            @Result(property = "name", column = "name")
    })
    Engineer selectById(Long id);

    // 查询全部（复用字段映射）
    @Select("SELECT * FROM tb_engineer")
    @ResultMap("engineerMap")
    List<Engineer> selectAll();
}
