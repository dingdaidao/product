package com.ding.demo2.service;

import com.ding.demo2.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
    @Autowired
    public UserDao mUserDao;

    public String login(String userName, String spwd) {
        return mUserDao.login(userName, spwd);
    }
}
