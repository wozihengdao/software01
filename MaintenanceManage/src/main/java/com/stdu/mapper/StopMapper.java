package com.stdu.mapper;


import com.stdu.pojo.Stop;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StopMapper {

    // 插入车站（自动生成ID）
    @Insert("INSERT INTO tb_stop (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Stop stop);

    // 根据ID删除车站
    @Delete("DELETE FROM tb_stop WHERE id = #{id}")
    int deleteById(Long id);

    // 更新车站信息
    @Update("UPDATE tb_stop SET name = #{name} WHERE id = #{id}")
    int update(Stop stop);

    // 查询所有车站
    @Select("SELECT * FROM tb_stop")
    @Results(id = "stopMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name")
    })
    List<Stop> selectAll();

    // 根据ID查询单个车站
    @Select("SELECT * FROM tb_stop WHERE id = #{id}")
    @ResultMap("stopMap")
    Stop selectById(Long id);

}