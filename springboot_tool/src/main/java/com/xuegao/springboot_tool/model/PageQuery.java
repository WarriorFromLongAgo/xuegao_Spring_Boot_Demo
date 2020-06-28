package com.xuegao.springboot_tool.model;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

// import javax.validation.Valid;

public class PageQuery<T> extends Page<T> {

    // @Valid
    private T queryData;

    // 自动优化 COUNT SQL
    private boolean optimizeCountSql = true;

    // 是否进行 count 查询
    private boolean isSearchCount = true;

    public PageQuery() {
        super(1, 10);
    }

    private PageQuery(T queryData) {
        this();
        setQueryData(queryData);
    }

    @ApiModelProperty(value = "当前页码(默认为1)", example = "1", position = 10)
    public long getPageNum() {
        return getCurrent();
    }

    public void setPageNum(int pageNum) {
        setCurrent(pageNum);
    }

    @ApiModelProperty(value = "每页行数(默认为10)", example = "10", position = 20)
    public long getPageSize() {
        return getSize();
    }

    public void setPageSize(int pageSize) {
        setSize(pageSize);
    }

    @ApiModelProperty(value = "条件对象", position = 30)
    public T getQueryData() {
        return queryData;
    }

    public void setQueryData(T queryData) {
        this.queryData = queryData;
    }

    public <U> PageInfo<U> toPageInfo(Class<U> clz) {
        return PageInfo.build(this, clz);
    }

    @JsonIgnore
    @Override
    public List<T> getRecords() {
        return super.getRecords();
    }

    @JsonIgnore
    @Override
    public long getTotal() {
        return super.getTotal();
    }

    @JsonIgnore
    @Override
    public long getSize() {
        return super.getSize();
    }

    @JsonIgnore
    @Override
    public long getCurrent() {
        return super.getCurrent();
    }

    @JsonIgnore
    @Override
    public List<OrderItem> getOrders() {
        return super.getOrders();
    }

    @JsonIgnore
    @Override
    public String[] ascs() {
        return super.ascs();
    }

    @JsonIgnore
    @Override
    public String[] descs() {
        return super.descs();
    }

    @JsonIgnore
    @Override
    public boolean optimizeCountSql() {
        return super.optimizeCountSql();
    }

    @JsonIgnore
    @Override
    public Page<T> setOptimizeCountSql(boolean optimizeCountSql) {
        return super.setOptimizeCountSql(optimizeCountSql);
    }

    @JsonIgnore
    @Override
    public long getPages() {
        return super.getPages();
    }

    @JsonIgnore
    @Override
    public boolean isSearchCount() {
        return super.isSearchCount();
    }
}