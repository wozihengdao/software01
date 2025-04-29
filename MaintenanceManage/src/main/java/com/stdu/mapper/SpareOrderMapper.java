package com.stdu.mapper;


import com.stdu.pojo.SpareOrder;
import com.stdu.pojo.UpkeepOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SpareOrderMapper {

    // 插入备件单（自动生成ID）
    @Insert("INSERT INTO tb_spare_order (stop, equipment_id, equipment_type, engineer_id, old_picture, new_picture, type, `data`) " +
            "VALUES (#{stop}, #{equipmentId}, #{equipmentType}, #{engineerId}, #{oldPicture}, #{newPicture}, #{type}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(SpareOrder order);

    // 根据ID删除
    @Delete("DELETE FROM tb_spare_order WHERE id = #{id}")
    int deleteById(Long id);

    // 更新全部字段
    @Update("UPDATE tb_spare_order SET " +
            "stop = #{stop}, " +
            "equipment_id = #{equipmentId}, " +
            "equipment_type = #{equipmentType}, " +
            "engineer_id = #{engineerId}, " +
            "old_picture = #{oldPicture}, " +
            "new_picture = #{newPicture}, " +
            "type = #{type}, " +
            "`data` = #{data} " +
            "WHERE id = #{id}")
    int update(SpareOrder order);

    // 查询所有备件单
    @Select("SELECT * FROM tb_spare_order")
    @Results(id = "spareOrderMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "stop", column = "stop"),
            @Result(property = "equipmentId", column = "equipment_id"),
            @Result(property = "equipmentType", column = "equipment_type"),
            @Result(property = "engineerId", column = "engineer_id"),
            @Result(property = "oldPicture", column = "old_picture"),
            @Result(property = "newPicture", column = "new_picture"),
            @Result(property = "type", column = "type"),
            @Result(property = "data", column = "data")
    })
    List<SpareOrder> selectAll();

    // 根据ID查询单个备件单
    @Select("SELECT * FROM tb_spare_order WHERE id = #{id}")
    @ResultMap("spareOrderMap")
    SpareOrder selectById(Long id);

    @Insert("INSERT INTO tb_spare_order (stop, equipment_id, equipment_type, type, data) " +
            "VALUES (#{stop}, #{equipmentId}, #{equipmentType}, #{type}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert2(SpareOrder order);

}