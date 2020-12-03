package com.xuegao.springboot_tool.model.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.vo
 * <br/> @ClassName：EasyExcelTeacher
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/03 10:50
 */
public class EasyExcelTeacher {

    // 老师的主键，不需要导出，可以忽略
    @ExcelIgnore
    private Integer teacherId;

    @ExcelProperty(value = "老师的名字")
    private String teacherName;
    @ExcelProperty(value = "老师的图片")
    private String teacherImage;
    @ExcelProperty(value = "老师状态")
    private String teacherStatus;

    public EasyExcelTeacher() {
    }

    @Override
    public String toString() {
        return "EasyExcelTeacher {" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherImage='" + teacherImage + '\'' +
                ", teacherStatus=" + teacherStatus +
                '}';
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherImage() {
        return teacherImage;
    }

    public void setTeacherImage(String teacherImage) {
        this.teacherImage = teacherImage;
    }

    public String getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(String teacherStatus) {
        this.teacherStatus = teacherStatus;
    }
}