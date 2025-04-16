package com.stdu.mapper;
import com.stdu.pojo.PatrolOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PatrolOrderMapper {

    // 插入巡检单（自动生成ID）
    @Insert("INSERT INTO tb_patrol_order (stop, equipment_id, equipment_type, engineer_id, picture, type, `data`) " +
            "VALUES (#{stop}, #{equipmentId}, #{equipmentType}, #{engineerId}, #{picture}, #{type}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(PatrolOrder order);

    // 根据ID删除
    @Delete("DELETE FROM tb_patrol_order WHERE id = #{id}")
    int deleteById(Long id);

    // 更新全部字段
    @Update("UPDATE tb_patrol_order SET " +
            "stop = #{stop}, " +
            "equipment_id = #{equipmentId}, " +
            "equipment_type = #{equipmentType}, " +
            "engineer_id = #{engineerId}, " +
            "picture = #{picture}, " +
            "type = #{type}, " +
            "`data` = #{data} " +
            "WHERE id = #{id}")
    int update(PatrolOrder order);

    // 查询所有巡检单
    @Select("SELECT * FROM tb_patrol_order")
    @Results(id = "patrolMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "stop", column = "stop"),
            @Result(property = "equipmentId", column = "equipment_id"),
            @Result(property = "equipmentType", column = "equipment_type"),
            @Result(property = "engineerId", column = "engineer_id"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "type", column = "type"),
            @Result(property = "data", column = "data")
    })
    List<PatrolOrder> selectAll();

    // 根据ID查询单个巡检单
    @Select("SELECT * FROM tb_patrol_order WHERE id = #{id}")
    @ResultMap("patrolMap")
    PatrolOrder selectById(Long id);
}