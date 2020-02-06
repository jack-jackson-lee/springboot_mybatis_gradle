package com.cn.example.springboot.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Excel公用类
 */

public class ExcelUtil {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    private static Workbook workbook;

    public static Map<String, List<List<String>>> readExcel(File file) {
        log.info("ExcelUtil readExcel  begin ");
        Map<String, List<List<String>>> map = new HashMap<>();
        try {
            workbook = WorkbookFactory.create(file);
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {
                    continue;
                }
                getSheetContent(sheet, map);
            }
            log.info("Excel2007Util readExcel  end ");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                log.error("ExcelUtil  workbook.close() : ", "IOException", e);
            }
        }
        log.info("ExcelUtil readExcel  null ");
        return null;
    }

    /**
     *
     * @param sheet
     * @param map  key -- sheetName  value -- data
     * @return
     */
    private static Map<String, List<List<String>>> getSheetContent(Sheet sheet, Map<String, List<List<String>>> map) {
        String sheetName = sheet.getSheetName();
        List<List<String>> result = new ArrayList<>();
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowNum; i++) {
            List<String> list = new ArrayList<>();
            Row row = sheet.getRow(i);
            if (null == row) {
                log.error("解析Excel失败，在第" + (i + 1) + "行没有读取到任何数据！");
            }
            int numberOfCells = row.getPhysicalNumberOfCells();
            for (int j = 0; j < numberOfCells; j++) {
                Cell cell = row.getCell(j);
                list.add(getCellResult(cell));
            }
            result.add(list);
        }
        map.put(sheetName, result);
        return map;
    }

    private static String getCellResult(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case NUMERIC: {
                DecimalFormat format = new DecimalFormat("0");
                return format.format(cell.getNumericCellValue());
            }
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = cell.getDateCellValue();
                return sdf.format(date);
            }
            default:
                break;
        }
        return "";
    }

    /**
     *
     * @param file  目标文件
     * @param map  key -- sheetName  value -- data
     * @return
     */
    public static boolean writeExcel(File file, Map<String, List<List<String>>> map) {
        log.info("ExcelUtil writeExcel begin");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook = new XSSFWorkbook();
            Set<String> sheets = map.keySet();
            for (String sheetName : sheets) {
                List<List<String>> lists = map.get(sheetName);
                Sheet sheet = workbook.createSheet(sheetName);
                for (int i = 0; i < lists.size(); i++) {
                    Row row = sheet.createRow(i);
                    if (i == 0) {
                        CellStyle style = workbook.createCellStyle();
                        Font font = workbook.createFont();
                        font.setBold(true);
                        style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                        style.setFont(font);
                        List<String> rowValue = lists.get(i);
                        for (int j = 0; j < rowValue.size(); j++) {
                            Cell cell = row.createCell(j);
                            cell.setCellValue(rowValue.get(j));
                            cell.setCellStyle(style);
                        }
                        continue;
                    }
                    List<String> rowValue = lists.get(i);
                    for (int j = 0; j < rowValue.size(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(rowValue.get(j));
                    }
                }
            }
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        log.info("ExcelUtil writeExcel end");
        return false;
    }

}
