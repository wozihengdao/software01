package com.stdu.mapper;

import com.stdu.pojo.TimeSel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TimeSelMapper {

    //初始化建立自动生成时间表
    @Insert("insert into  tb_timesel (day, orderType,templateOrderId,lastGenerateTime) VALUES (#{day},#{orderType},#{templateOrderId},#{lastGenerateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void init(TimeSel timeSel);

    //根据工单类型删除自动生成时间表
    @Delete("delete from tb_timesel where orderType = #{orderType}") // 修复参数绑定
    void deleteByOrderType(int orderType);

    //更新对应工单的自动生成日期
    @Update("UPDATE tb_timesel SET day=#{day} , templateOrderId=#{templateOrderId},lastGenerateTime=#{lastGenerateTime} where orderType=#{orderType}")
        void update(TimeSel timeSel);

    //获取所有的自动生成日期
    @Select("select * from tb_timesel")
    @ResultMap("TimeSelResultlMap")
    List<TimeSel> selectAll();
    //根据用户类型查询对应的生成时间表
    @Select("select * from tb_timesel where orderType = #{orderType}")
    @ResultMap("TimeSelResultlMap")
    TimeSel selectByOrderType(String orderType);

    @Select("select * from tb_timesel where orderType = #{orderType} and templateOrderId=#{templateOrderId}")
    @ResultMap("TimeSelResultlMap")
    TimeSel selectByOrderTypeAndTemplateOrderId(String orderType, String templateOrderId);

}
