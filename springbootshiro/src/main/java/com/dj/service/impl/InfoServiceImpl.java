package com.dj.service.impl;

import com.dj.entity.Info;
import com.dj.dao.InfoDao;
import com.dj.service.InfoService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

 /**
 * @ClassName InfoServiceImpl
 * @Description (Info)表服务实现类
 * @author joker_dj
 * @date 2020-04-13 03:15:50
 * @Version 1.0
 **/
@Service("infoService")
public class InfoServiceImpl  implements InfoService {

    @Autowired
    protected InfoDao infoDao;

    /**
     * @Description 添加Info
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param info 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Info info) {
        if(infoDao.insert(info) == 1){
            return true;
        }
        return false;
    }

    /**
     * @Description 删除Info
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param  主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById( ) {
        if(infoDao.deleteById() == 1){
            return true;
        }
        return false;
    }

    /**
     * @Description 查询单条数据
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param  主键
     * @return 实例对象
     */
    @Override
    public Info queryById( ) {
        return infoDao.queryById();
    }

    /**
     * @Description 查询全部数据
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    @Override
    public List<Info> queryAll() {
        return infoDao.queryAll();
    }

    /**
     * @Description 实体作为筛选条件查询数据
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param info 实例对象
     * @return 对象列表
     */
    @Override
    public List<Info> queryAll(Info info) {
        return infoDao.queryAll(info);
    }

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author joker_dj
     * @date 2020-04-13 03:15:50
     * @param info 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Info info) {
        if(infoDao.update(info) == 1){
            return true;
        }
        return false;
    }

     @Override
     public Info query(String username) {
         return infoDao.query(username);
     }

 }