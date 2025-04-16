package com.stdu.mapper;

import com.stdu.pojo.Spare;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface SpareMapper {

        // 插入备件（自动生成ID）
        @Insert("INSERT INTO tb_spare (name, type, `data`) VALUES (#{name}, #{type}, #{data})")
        @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
        int insert(Spare spare);

        // 根据ID删除备件
        @Delete("DELETE FROM tb_spare WHERE id = #{id}")
        int deleteById(Long id);

        // 更新备件信息
        @Update("UPDATE tb_spare SET " +
                "name = #{name}, " +
                "type = #{type}, " +
                "`data` = #{data} " +
                "WHERE id = #{id}")
        int update(Spare spare);

        // 查询所有备件
        @Select("SELECT * FROM tb_spare")
        @Results(id = "spareMap", value = {
                @Result(property = "id", column = "id", id = true),
                @Result(property = "name", column = "name"),
                @Result(property = "type", column = "type"),
                @Result(property = "data", column = "data")
        })
        List<Spare> selectAll();

        // 根据ID查询单个备件
        @Select("SELECT * FROM tb_spare WHERE id = #{id}")
        @ResultMap("spareMap")
        Spare selectById(Long id);

}
