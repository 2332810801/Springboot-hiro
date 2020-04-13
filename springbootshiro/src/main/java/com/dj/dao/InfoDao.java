package com.dj.dao;

import com.dj.entity.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

 /**
 * @InterfaceName InfoDao
 * @Description (Info)表数据库访问层
 * @author joker_dj
 * @date 2020-04-13 03:15:50
 * @Version 1.0
 **/
@Mapper
public interface InfoDao {

    /**
     * @Description 添加Info
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param info 实例对象
     * @return 影响行数
     */
    int insert(Info info);
    
    /**
     * @Description 删除Info
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param  主键
     * @return 影响行数
     */
    int deleteById( );

    /**
     * @Description 查询单条数据
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param  主键
     * @return 实例对象
     */
    Info queryById( );

    /**
     * @Description 查询全部数据
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Info> queryAll();

    /**
     * @Description 实体作为筛选条件查询数据
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param info 实例对象
     * @return 对象列表
     */
    List<Info> queryAll(Info info);

    /**
     * @Description 修改Info
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param 根据info的主键修改数据
     * @return 影响行数
     */
    int update(Info info);

     @Select("select * from Info where username=#{username} ")
     Info query(@Param("username") String username);
 }