package com.xuegao.springboot_tool.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：EasyExcelUtils
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/10 16:28
 */
public class EasyExcelUtils {

    private EasyExcelUtils() {
    }

    /**
     * <br/> @Title: 单个工作簿 导出Excel
     * <br/> @Description:
     * <br/> @MethodName: exportExcel
     * <br/> @param response:
     * <br/> @param fileName: 文件名称
     * <br/> @param sheetName: 工作簿名称
     * <br/> @param dateList: 数据list
     * <br/> @param head: 传进来的 对象名称
     * <br/> @Return: void
     * <br/> @author: 80004960
     * <br/> @date: 2020/12/10 15:09
     */
    public static void exportExcel(HttpServletResponse response, String fileName, String sheetName, List<?> dateList, Class head) {
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
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "filename=" + fileName + ".xlsx");
            EasyExcelFactory
                    .write(response.getOutputStream(), head)
                    // 注册上面的头样式，内容样式
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    // 自动扩展列的长度
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet(sheetName)
                    .doWrite(dateList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}