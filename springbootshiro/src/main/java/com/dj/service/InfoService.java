package com.dj.service;

import com.dj.entity.Info;
import java.util.List;

/**
 * @InterfaceName InfoService
 * @Description (Info)表服务接口
 * @author joker_dj
 * @date 2020-04-13 03:15:50
 * @Version 1.0
 **/
public interface InfoService {

    /**
     * @Description 添加Info
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param info 实例对象
     * @return 是否成功
     */
    boolean insert(Info info);

    /**
     * @Description 删除Info
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param  主键
     * @return 是否成功
     */
    boolean deleteById( );

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
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param info 实例对象
     * @return 是否成功
     */
    boolean update(Info info);

    Info query(String username);

}