package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExcelLogger {

    // Save file to user's desktop
    private static final String FILE_NAME = System.getProperty("user.home") + "/Desktop/api_downtime_log.xlsx";

    public static void log(String apiName, String url, int statusCode, String message) {
        try {
            File file = new File(FILE_NAME);
            Workbook workbook;
            Sheet sheet;

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Downtime Log");

                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Timestamp");
                header.createCell(1).setCellValue("API Name");
                header.createCell(2).setCellValue("URL");
                header.createCell(3).setCellValue("Status Code");
                header.createCell(4).setCellValue("Message");
            }

            int lastRow = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRow + 1);
            row.createCell(0).setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            row.createCell(1).setCellValue(apiName);
            row.createCell(2).setCellValue(url);
            row.createCell(3).setCellValue(statusCode);
            row.createCell(4).setCellValue(message);

            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            workbook.write(fos);
            fos.flush();
            fos.close();
            workbook.close();

            System.out.println("✅ Log written to Excel: " + FILE_NAME);

        } catch (Exception e) {
            System.out.println("❌ Excel log failed: " + e.getMessage());
            e.printStackTrace();  // Add this for debugging
        }
    }
}
