package com.stdu.mapper;


import com.stdu.pojo.MaintenanceOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MaintenanceOrderMapper {

    // 插入维修单（自动生成ID）
    @Insert("INSERT INTO tb_maintenance_order (repair_id, engineer_id, picture, type, `data`) " +
            "VALUES (#{repairId}, #{engineerId}, #{picture}, #{type}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MaintenanceOrder order);

    // 根据ID删除
    @Delete("DELETE FROM tb_maintenance_order WHERE id = #{id}")
    int deleteById(Long id);

    // 更新全部字段
    @Update("UPDATE tb_maintenance_order SET " +
            "repair_id = #{repairId}, " +
            "engineer_id = #{engineerId}, " +
            "picture = #{picture}, " +
            "type = #{type}, " +
            "`data` = #{data} " +
            "WHERE id = #{id}")
    int update(MaintenanceOrder order);

    // 查询所有维修单
    @Select("SELECT * FROM tb_maintenance_order")
    @Results(id = "orderMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "repairId", column = "repair_id"),
            @Result(property = "engineerId", column = "engineer_id"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "type", column = "type"),
            @Result(property = "data", column = "data")
    })
    List<MaintenanceOrder> selectAll();

    // 根据ID查询单个维修单
    @Select("SELECT * FROM tb_maintenance_order WHERE id = #{id}")
    @ResultMap("orderMap")
    MaintenanceOrder selectById(Long id);
}