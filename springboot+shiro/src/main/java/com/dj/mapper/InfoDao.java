package com.dj.mapper;

import com.dj.pojo.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author joker_dj
 * @create 2020-04-13日 11:54
 */
@Mapper
public interface InfoDao {

    @Select("select * from Info where username=#{username} ")
    Info queryByName(String username);
}
