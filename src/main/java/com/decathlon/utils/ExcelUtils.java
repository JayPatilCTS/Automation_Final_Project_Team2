package com.decathlon.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

    public class ExcelUtils {
        public static List<String> getInvalidEmails(String filePath) throws Exception {
            List<String> data = new ArrayList<>();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\testData\\invalidUserCredentials.xlsx");
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    data.add(row.getCell(0).getStringCellValue());
                }
            }
            workbook.close();
            return data;
        }
    }

