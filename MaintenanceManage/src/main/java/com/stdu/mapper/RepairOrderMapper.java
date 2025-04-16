package com.stdu.mapper;

import com.stdu.pojo.RepairOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RepairOrderMapper {

    // 查询所有报修单 [1,2](@ref)
    @Select("SELECT * FROM tb_repair_order")
    @Results(id = "repairOrderMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "stop", column = "stop"),
            @Result(property = "faultyEquipmentId", column = "faulty_equipment_id"),
            @Result(property = "faultyType", column = "faulty_type"),
            @Result(property = "faultyDescription", column = "faulty_description"),
            @Result(property = "faultyGrade", column = "faulty_grade"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "type", column = "type"),
            @Result(property = "data", column = "data")
    })
    List<RepairOrder> findAll();

    // 按 ID 查询 [3,7](@ref)
    @Select("SELECT * FROM tb_repair_order WHERE id = #{id}")
    @ResultMap("repairOrderMap")
    RepairOrder findById(@Param("id") Long id);

    // 插入报修单（ID 自增）[1,15](@ref)
    @Insert("INSERT INTO tb_repair_order (stop, faulty_equipment_id, faulty_type, faulty_description, " +
            "faulty_grade, picture, type, data) " +
            "VALUES (#{stop}, #{faultyEquipmentId}, #{faultyType}, #{faultyDescription}, " +
            "#{faultyGrade}, #{picture}, #{type}, #{data})")
    int insert(RepairOrder order);

    // 修改报修单 [16,17](@ref)
    @Update("UPDATE tb_repair_order SET " +
            "stop = #{stop}, faulty_equipment_id = #{faultyEquipmentId}, " +
            "faulty_type = #{faultyType}, faulty_description = #{faultyDescription}, " +
            "faulty_grade = #{faultyGrade}, picture = #{picture}, " +
            "type = #{type}, data = #{data} " +
            "WHERE id = #{id}")
    int update(RepairOrder order);

    // 删除报修单 [18,19](@ref)
    @Delete("DELETE FROM tb_repair_order WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
}