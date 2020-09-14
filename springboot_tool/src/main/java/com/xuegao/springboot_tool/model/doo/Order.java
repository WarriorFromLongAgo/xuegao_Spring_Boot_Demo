package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.doo
 * <br/> @ClassName：Order
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/30 14:19
 */
@TableName("t_order")
public class Order implements Serializable {
    private static final long serialVersionUID = -8413909223245101894L;

    @TableId
    @ApiModelProperty(value = "订单id", position = 10)
    private Long id;

    @TableField("product_id")
    @ApiModelProperty(value = "商品id", position = 10)
    private Long productId;

    @TableField("number")
    @ApiModelProperty(value = "商品数量", position = 10)
    private Integer number;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", number=" + number +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}