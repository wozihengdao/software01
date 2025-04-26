package com.stdu.mapper;


import com.stdu.pojo.Maintenance;
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

    @Select("SELECT mo.id, mo.engineer_id as engineerId, mo.picture as newPicture, mo.type, mo.data, ro.stop, ro.faulty_equipment_id as faultyEquipmentId, ro.faulty_type as faultyType, ro.faulty_description as faultyDescription, ro.faulty_grade as faultyGrade, ro.picture as olderPicture FROM tb_maintenance_order mo INNER JOIN tb_repair_order ro ON CAST(mo.repair_id AS SIGNED) = ro.id WHERE mo.id = #{orderId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "engineerId", column = "engineerId"),
            @Result(property = "newPicture", column = "newPicture"),
            @Result(property = "type", column = "type"),
            @Result(property = "data", column = "data"),
            @Result(property = "stop", column = "stop"),
            @Result(property = "faultyEquipmentId", column = "faultyEquipmentId"),
            @Result(property = "faultyType", column = "faultyType"),
            @Result(property = "faultyDescription", column = "faultyDescription"),
            @Result(property = "faultyGrade", column = "faultyGrade"),
            @Result(property = "olderPicture", column = "olderPicture")
    })
    Maintenance selectAllMaintenanceById(String orderId);


}