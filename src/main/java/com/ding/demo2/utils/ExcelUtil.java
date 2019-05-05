package com.ding.demo2.utils;

import com.ding.demo2.entity.Product;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 路径：com.example.demo.utils
 * 类名：
 * 功能：导入导出
 * 备注：
 * 创建人：typ
 * 创建时间：2018/10/19 11:21
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
public class ExcelUtil {

    /**
     * 方法名：exportExcel
     * 功能：导出Excel
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:00
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
   /* public static void exportExcel(HttpServletResponse response, ExcelData data) {
        System.out.println("导出解析开始，fileName:" + data.getFileName());
        try {
            //实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet("sheet");
            //设置表头
            setTitle(workbook, sheet, data.getHead());
            //设置单元格并赋值
            setData(sheet, data.getData());
            //设置浏览器下载
            setBrowser(response, workbook, data.getFileName());
            System.out.println("导出解析成功!");
        } catch (Exception e) {
            System.out.println("导出解析失败!");
            e.printStackTrace();
        }
    }*/

    /**
     * 方法名：setTitle
     * 功能：设置表头
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 10:20
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
        try {
            HSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= str.length; i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            //设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            //创建表头名称
            HSSFCell cell;
            for (int j = 0; j < str.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(str[j]);
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            System.out.println("导出时设置表头失败！");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setData
     * 功能：表格赋值
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:11
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setData(HSSFSheet sheet, List<String[]> data) {
        try {
            int rowNum = 1;
            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(rowNum);
                for (int j = 0; j < data.get(i).length; j++) {
                    row.createCell(j).setCellValue(data.get(i)[j]);
                }
                rowNum++;
            }
            System.out.println("表格赋值成功！");
        } catch (Exception e) {
            System.out.println("表格赋值失败！");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setBrowser
     * 功能：使用浏览器下载
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:20
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        try {
            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
            System.out.println("设置浏览器下载成功！");
        } catch (Exception e) {
            System.out.println("设置浏览器下载失败！");
            e.printStackTrace();
        }

    }


    /**
     * 方法名：importExcel
     * 功能：导入
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 11:45
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    public static List<Product> importExcel(String fileName) {
        InputStream file = null;
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Product> answers = new ArrayList<>();
        try {
            Workbook workbook = null;
            try {
                workbook = WorkbookFactory.create((file));
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            //有多少个sheet
            int sheets = workbook.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                //获取多少行
                int rows = sheet.getPhysicalNumberOfRows();
                Product answer = null;
                //遍历每一行，注意：第 0 行为标题
                for (int j = 1; j < rows; j++) {
                    answer = new Product();
                    //获得第 j 行
                    Row row = sheet.getRow(j);
                    answer.setNo(row.getCell(0).getStringCellValue());
                    answer.setName(row.getCell(1).getStringCellValue());
                    answer.setSize(row.getCell(2).getStringCellValue());
                    answer.setUpdatetime(row.getCell(3).getDateCellValue());
                    answer.setCount((int)row.getCell(4).getNumericCellValue());
                    answers.add(answer);
                    System.out.println(answer.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

}