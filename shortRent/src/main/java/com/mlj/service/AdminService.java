package com.mlj.service;

import com.mlj.dao.AdminDao;
import com.mlj.modle.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    public Admin get(String account, String pass) {
        return adminDao.get(account, pass);
    }
}
