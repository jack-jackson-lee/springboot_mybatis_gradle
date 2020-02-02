package com.cn.example.springboot.dao;

import org.apache.ibatis.annotations.Param;

public interface IStudentDao {


    public int createStudent();

    public int dropStudent(@Param("tableName") String tableName);



}
