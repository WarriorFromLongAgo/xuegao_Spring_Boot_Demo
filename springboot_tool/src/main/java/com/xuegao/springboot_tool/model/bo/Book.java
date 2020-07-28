package com.xuegao.springboot_tool.model.bo;

import org.nustaq.serialization.annotations.Version;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Description: $VAL$ .<br>
 *
 * @author m-xy
 * Created By 2019/8/20 下午4:12
 */
public class Book implements Serializable {
    private String name;
    private int number;
    private long version;
    private Date createTime;
    private LocalDateTime updateTime;
    private List<String> list;
    /**
     * FST 可以增加版本 Version 对新增加的字段进行管理，这样反序列化就不会报错了
     * 付出性能的成本。
     */
    @Version(1)
    private Date empty;

    public Book() {
    }

    public Book(String name, int number, long version, Date createTime, LocalDateTime updateTime, List<String> list, Date empty) {
        this.name = name;
        this.number = number;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.list = list;
        this.empty = empty;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", version=" + version +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", list=" + list +
                ", empty=" + empty +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Date getEmpty() {
        return empty;
    }

    public void setEmpty(Date empty) {
        this.empty = empty;
    }
}
