package com.xuegao.xuegaomybatis.domain;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.doo
 * <br/> @ClassName：Product
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/24 10:15
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 7250019257709660558L;

    private Long id;

    private String name;

    private String price;

    private Integer stocks;

    private String description;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", stocks=" + stocks +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}