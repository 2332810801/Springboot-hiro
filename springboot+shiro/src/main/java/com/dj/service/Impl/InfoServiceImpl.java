package com.dj.service.Impl;

import com.dj.mapper.InfoDao;
import com.dj.pojo.Info;
import com.dj.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joker_dj
 * @create 2020-04-13日 12:01
 */
@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    InfoDao dao;
    @Override
    public Info queryByName(String username) {
        return dao.queryByName(username);
    }
}
