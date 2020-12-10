package com.xuegao.springboot_tool.controller.execl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.xuegao.springboot_tool.model.vo.EasyExcelTeacher;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller.execl
 * <br/> @ClassName：PoiExportExeclController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/03 10:45
 */
@RestController
@RequestMapping(value = "easy_excel")
public class EasyExcelExportExcelController {

    @RequestMapping("11")
    public void EasyExcelExportExcel(HttpServletResponse response, HttpServletRequest request) {
        List<EasyExcelTeacher> easyExcelTeacherList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            EasyExcelTeacher easyExcelTeacher = new EasyExcelTeacher();
            easyExcelTeacher.setTeacherId(i + 1);
            easyExcelTeacher.setTeacherName(String.valueOf(i + 1));
            easyExcelTeacher.setTeacherImage(String.valueOf(i + 1));
            easyExcelTeacher.setTeacherStatus(String.valueOf(i + 1));
            easyExcelTeacherList.add(easyExcelTeacher);
        }
        execl(response, easyExcelTeacherList);

    }

    private void execl(HttpServletResponse response, List<EasyExcelTeacher> easyExcelTeacherList) {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteFont.setBold(false);
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setShrinkToFit(true);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 12);
        // 垂直居中,水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // 表格线
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);

        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        try {
            String fileName = "hhhh";
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "filename=" + fileName + ".xlsx");
            EasyExcel
                    .write(response.getOutputStream(), EasyExcelTeacher.class)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet("sheet模板")
                    .doWrite(easyExcelTeacherList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("22")
    public void EasyExcelExportExcel2(HttpServletResponse response, HttpServletRequest request) {
        List<EasyExcelTeacher> easyExcelTeacherList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            EasyExcelTeacher easyExcelTeacher = new EasyExcelTeacher();
            easyExcelTeacher.setTeacherId(i + 1);
            easyExcelTeacher.setTeacherName(String.valueOf(i + 1));
            easyExcelTeacher.setTeacherImage(String.valueOf(i + 1));
            easyExcelTeacher.setTeacherStatus(String.valueOf(i + 1));
            easyExcelTeacherList.add(easyExcelTeacher);
        }
        try {
            String fileName = "hhhh";
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "filename=" + fileName + ".xlsx");

            //新建ExcelWriter
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

            //获取sheet0对象
            WriteSheet mainSheet = EasyExcel.writerSheet(0, "数据目录").head(EasyExcelTeacher.class)
                    //设置拦截器或自定义样式
                    .registerWriteHandler(getStyleStrategy()).build();

            //向sheet0写入数据 传入空list这样只导出表头
            excelWriter.write(easyExcelTeacherList, mainSheet);
            //获取sheet1对象
            WriteSheet detailSheet = EasyExcel.writerSheet(1, "信息项").head(EasyExcelTeacher.class)
                    //设置拦截器或自定义样式
                    .registerWriteHandler(getStyleStrategy())
                    .build();
            //向sheet1写入数据 传入空list这样只导出表头
            excelWriter.write(easyExcelTeacherList, detailSheet);
            //关闭流
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //设置样式 去除默认表头样式及设置内容居中
    public static HorizontalCellStyleStrategy getStyleStrategy() {
        // 内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        // 设置背景色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());

        // 垂直居中,水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // 表格线
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);

        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);

        // 字体策略
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 12);
        // 字体样式
        // contentWriteFont.setFontName("Frozen");
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        // 头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());

        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
}