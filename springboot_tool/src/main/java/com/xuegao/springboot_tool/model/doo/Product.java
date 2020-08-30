package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.doo
 * <br/> @ClassName：Product
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/8/24 10:15
 */
@TableName("t_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 7250019257709660558L;

    @TableId
    @ApiModelProperty(value = "商品id", position = 10)
    private Long id;

    @TableField("name")
    @ApiModelProperty(value = "商品名称", position = 10)
    private String name;

    @TableField("price")
    @ApiModelProperty(value = "商品价格", position = 10)
    private String price;

    @TableField("stocks")
    @ApiModelProperty(value = "商品库存", position = 10)
    private Integer stocks;

    @TableField("description")
    @ApiModelProperty(value = "商品描述", position = 10)
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