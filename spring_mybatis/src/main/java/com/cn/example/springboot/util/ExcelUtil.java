package com.cn.example.springboot.util;

import com.cn.example.springboot.common.CommonConstant;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    public static Map<String, List<List<String>>> readExcel(File file) {
        String fileName = file.getName();
        if (fileName.endsWith(CommonConstant.EXCEL_2003_SUFFIX)) {
            return Excel2003Util.readXls(file);
        } else if (fileName.endsWith(CommonConstant.EXCEL_2003_SUFFIX)) {
            return Excel2007Util.readXlsx(file);
        }
        return null;
    }


}
