package com.cn.example.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class PathUtil {

    /**
     * 这是slf4j的接口，由于我们引入了logback-classic依赖，所以底层实现是logback
     */
    private static final Logger log = LoggerFactory.getLogger(PathUtil.class);



    public static void basePath() {
        try{
            //没有参数
            File fileCreateByNo=new File("");
           log.info("fileCreateByNo=="+fileCreateByNo);
           log.info("fileCreateByNo=="+fileCreateByNo.getCanonicalPath());
           log.info("-----------------------------");
            //一个点的参数
            File fileCreateByPoint=new File(".");
           log.info("fileCreateByPoint=="+fileCreateByPoint);
           log.info("fileCreateByPoint=="+fileCreateByPoint.getCanonicalPath());
           log.info("-----------------------------");
            //两个点的参数
            File fileTwoPoint = new File("..");
           log.info("fileTwoPoint=="+fileTwoPoint);
           log.info("fileTwoPoint=="+fileTwoPoint.getCanonicalPath());
           log.info("-----------------------------");
            //一个点两条斜线的参数
            File filePLL = new File(".\\");
           log.info("filePLL=="+filePLL);
           log.info("filePLL=="+filePLL.getCanonicalPath());
           log.info("-----------------------------");
            //当前工作目录
            String currentWorkPath=System.getProperty("user.dir");
           log.info("currentWorkPath=="+currentWorkPath);
        }catch(Exception e){
           log.info("IOException....出问题咯");
        }
    }

    public static void main(String[] args)  {
        basePath();
        log.info("E:\\projects\\springboot\\spring_mybatis\\src\\main\\resources\\application.yml");
    }
}
