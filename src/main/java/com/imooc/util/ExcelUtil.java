package com.imooc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imooc.model.DocDrawing;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by zxw on 2017/8/11.
 */
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static boolean createExcelFile(String excelPath) {
        boolean isCreateSuccess = false;
        Workbook workbook = null;
        try {
            // XSSFWork used for .xslx (>= 2007), HSSWorkbook for 03 .xsl
            workbook = new XSSFWorkbook();//HSSFWorkbook();//WorkbookFactory.create(inputStream);
        }catch(Exception e) {
            System.out.println("It cause Error on CREATING excel workbook: ");
            e.printStackTrace();
        }
        if(workbook != null) {
            Sheet sheet = workbook.createSheet("drawinginfo");

            try {
                FileOutputStream outputStream = new FileOutputStream(excelPath);
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
                isCreateSuccess = true;
            } catch (Exception e) {
                System.out.println("It cause Error on WRITTING excel workbook: ");
                e.printStackTrace();
            }
        }
        File sss = new File(excelPath);
        System.out.println(sss.getAbsolutePath());
        return isCreateSuccess;
    }

    public static void writeExcel(List<DocDrawing> dataList, int cloumnCount, String finalXlsxPath){

        OutputStream out = null;

        try {
            // 获取总列数
            int columnNumCount = cloumnCount;

            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);

            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);

            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();  // 第一行从0开始算，第一行是属性列
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 0; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                if (row != null)
                    sheet.removeRow(row);
            }



            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);

            String[] titleArray = {"工程编号", "工程名称", "项目编号", "项目名称", "图纸编号", "图纸名称", "版次", "阶段名称",
                    "专业名称", "图幅", "设计人", "绘图人", "校对人", "审定人", "审核人", "工程负责人",
                    "专业负责人", "设计总负责人", "归档日期", "出图日期", "路径"};

            Row titleRow = sheet.createRow(0);
            for (int p = 0; p < cloumnCount; p ++) {
                titleRow.createCell(p).setCellValue(titleArray[p]);
            }
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                DocDrawing data = dataList.get(j);


                for (int k = 0; k <= columnNumCount; k++) {
                    // 在一行内循环

                    // 工程编号
                    Cell cell_1 = row.createCell(0);
                    cell_1.setCellValue(data.getProjectnumber1());

                    // 工程名称
                    Cell cell_2 = row.createCell(1);
                    cell_2.setCellValue(data.getProjectname());

                    // 项目编号
                    Cell cell_3 = row.createCell(2);
                    cell_3.setCellValue(data.getProjectnumber());

                    // 项目名称
                    Cell cell_4 = row.createCell(3);
                    cell_4.setCellValue(data.getItemname());

                    // 图纸编号
                    Cell cell_5 = row.createCell(4);
                    cell_5.setCellValue(data.getPicnumber());

                    // 图纸名称
                    Cell cell_6 = row.createCell(5);
                    cell_6.setCellValue(data.getPicname());

                    // 版次
                    Cell cell_7 = row.createCell(6);
                    cell_7.setCellValue(data.getVersion());

                    // 阶段名称
                    Cell cell_8 = row.createCell(7);
                    cell_8.setCellValue(data.getPhrasename());

                    // 专业名称
                    Cell cell_9 = row.createCell(8);
                    cell_9.setCellValue(data.getSpecname());

                    // 图幅
                    Cell cell_10 = row.createCell(9);
                    cell_10.setCellValue(data.getConvertpapertype());

                    // 设计人
                    Cell cell_11 = row.createCell(10);
                    cell_11.setCellValue(data.getCreator());

                    // 绘图人
                    Cell cell_12 = row.createCell(11);
                    cell_12.setCellValue(data.getEditor());

                    // 校对人
                    Cell cell_13 = row.createCell(12);
                    cell_13.setCellValue(data.getCollator());

                    // 审定人
                    Cell cell_14 = row.createCell(13);
                    cell_14.setCellValue(data.getReader());

                    // 审核人
                    Cell cell_15 = row.createCell(14);
                    cell_15.setCellValue(data.getAuditor());

                    // 工程负责人
                    Cell cell_16 = row.createCell(15);
                    cell_16.setCellValue(data.getItemperson());

                    // 专业负责人
                    Cell cell_17 = row.createCell(16);
                    cell_17.setCellValue(data.getSpecialperson());

                    // 设计总负责人
                    Cell cell_18 = row.createCell(17);
                    cell_18.setCellValue(data.getDesignmater());

                    // 归档日期
                    Cell cell_19 = row.createCell(18);
                    cell_19.setCellValue(data.getGuidangdate());

                    // 出图日期
                    Cell cell_20 = row.createCell(19);
                    cell_20.setCellValue(data.getPhdate());

                    // 路径
                    Cell cell_21 = row.createCell(20);
                    cell_21.setCellValue(data.getPath());

                }
            }

            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);


        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("数据导出成功");

    }

    /**
     * 判断Excel的版本,获取Workbook
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
