package com.stdu.mapper;

import com.stdu.pojo.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EquipmentMapper {
    // 插入设备（自动生成ID）
    @Insert("INSERT INTO tb_equipment (stop, name, type) " +
            "VALUES (#{stop}, #{name}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Equipment equipment);

    // 根据ID删除
    @Delete("DELETE FROM tb_equipment WHERE id = #{id}")
    int deleteById(Long id);

    // 更新全部字段
    @Update("UPDATE tb_equipment SET " +
            "stop = #{stop}, " +
            "name = #{name}, " +
            "type = #{type} " +
            "WHERE id = #{id}")
    int update(Equipment equipment);

    // 查询所有设备（带字段映射）
    @Select("SELECT * FROM tb_equipment")
    @Results(id = "equipmentMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "stop", column = "stop"),
            @Result(property = "name", column = "name"),
            @Result(property = "type", column = "type")
    })
    List<Equipment> selectAll();

    // 根据ID查询单个设备
    @Select("SELECT * FROM tb_equipment WHERE id = #{id}")
    @ResultMap("equipmentMap")
    Equipment selectById(Long id);

}
