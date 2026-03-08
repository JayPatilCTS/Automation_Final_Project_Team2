package com.decathlon.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

    public class ExcelUtils {
        public static List<String> getInvalidEmails(String filePath) throws Exception {
            List<String> data = new ArrayList<>();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\testData\\invalidUserCredentials.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh= wb.getSheet("Sheet1");

            for (int i = 1; i <= sh.getLastRowNum(); i++) {
                Row row = sh.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    data.add(row.getCell(0).getStringCellValue());
                }
            }
            wb.close();
            return data;
        }
    }

