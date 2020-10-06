package com.xuegao.springboot_tool.model.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CallCdr {
    private String name;
    private int age;
    private String wife;
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

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
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
        this.putTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
}