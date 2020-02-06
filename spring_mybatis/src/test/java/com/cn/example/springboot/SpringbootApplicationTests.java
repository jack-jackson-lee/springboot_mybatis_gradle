package com.cn.example.springboot;

import com.cn.example.springboot.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {


    private static final Logger log = LoggerFactory.getLogger(SpringbootApplicationTests.class);

    @Autowired
    private StudentServiceImpl service;

    @Test
    void contextLoads() {
    }

    @Test
    public void ctreateTable() {
        int student = service.createStudent();
        log.info("ctreateTable>>>>>" + student);
    }

    @Test
    void dropTable() {
        int student = service.dropStudent("student");
        log.info("dropTable>>>>>" + student);
    }



}
