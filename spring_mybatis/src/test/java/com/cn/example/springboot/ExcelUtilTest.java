package com.cn.example.springboot;

import com.alibaba.fastjson.JSON;
import com.cn.example.springboot.common.CommonConstant;
import com.cn.example.springboot.util.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExcelUtilTest {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtilTest.class);


    @Test
    public void readExcelUtil(){
        File file = new File("E:\\projects\\springboot\\springboot_mybatis_gradle\\spring_mybatis\\src\\test\\java\\com\\cn\\example\\springboot\\file\\学生成绩表.xlsx");
        Map<String, List<List<String>>> map = ExcelUtil.readExcel(file);
        log.info(JSON.toJSONString(map));
    }

    @Test
    public void writeExcelUtil(){
        File srcfile = new File( CommonConstant.TEST_RESULT_PATh+"学生成绩表.xlsx");
        String resultFilePath = CommonConstant.TEST_RESULT_PATh+"result"+CommonConstant.EXCEL_2007_SUFFIX;
        File targetfile = new File(resultFilePath);
        Map<String, List<List<String>>> map = ExcelUtil.readExcel(srcfile);
        ExcelUtil.writeExcel(targetfile,map);
    }
}
