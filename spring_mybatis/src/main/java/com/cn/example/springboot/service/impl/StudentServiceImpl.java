package com.cn.example.springboot.service.impl;

import com.cn.example.springboot.dao.IStudentDao;
import com.cn.example.springboot.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {


    @Autowired
    private IStudentDao studentDao;

    @Override
    public int createStudent() {
        return studentDao.createStudent();
    }

    @Override
    public int dropStudent(String tableName) {
        return studentDao.dropStudent(tableName);
    }
}
