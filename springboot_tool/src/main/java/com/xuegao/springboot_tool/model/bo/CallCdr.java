package com.xuegao.springboot_tool.model.bo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CallCdr {
    private String name;
    private int age;
    private Boolean zhiFu;
    private Double salary;
    private String putTime;

    public CallCdr() {
    }

    public CallCdr(Double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getZhiFu() {
        return zhiFu;
    }

    public void setZhiFu(Boolean zhiFu) {
        this.zhiFu = zhiFu;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPutTime() {
        return putTime;
    }

    public void setPutTime() {
        this.putTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }
}