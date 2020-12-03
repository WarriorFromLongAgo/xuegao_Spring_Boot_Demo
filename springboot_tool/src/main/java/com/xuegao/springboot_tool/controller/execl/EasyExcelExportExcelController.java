package com.xuegao.springboot_tool.controller.execl;

import com.alibaba.excel.EasyExcel;
import com.xuegao.springboot_tool.model.vo.EasyExcelTeacher;
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
        try {
            String fileName = "hhhh";
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), EasyExcelTeacher.class).sheet("sheet模板").doWrite(easyExcelTeacherList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}