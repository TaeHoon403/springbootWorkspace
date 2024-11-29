package com.kh.app04.home.service;

import com.kh.app04.home.dao.HomeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @Autowired
    private HomeDao dao;

    public void home(){
        System.out.println("home service 호출 됨~~~");

        dao.home();

    }


}
