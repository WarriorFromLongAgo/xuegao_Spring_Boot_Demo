package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.sf.edu.domain.doo.train
 * <br/> @ClassName：YzyOrder
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/29 18:34:29
 */
@ApiModel(value = "null")
@TableName("yzy_order")
public class YzyOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    @ApiModelProperty(value = "null")
    private Integer id;

    @TableField("`order_id`")
    @ApiModelProperty(value = "订单id")
    private String orderId;

    @TableField("`buy_num`")
    @ApiModelProperty(value = "购买数量")
    private Integer buyNum;

    @TableField("`good_id`")
    @ApiModelProperty(value = "商品ID")
    private Integer goodId;

    @TableField("`user_id`")
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @TableField("`pay_status`")
    @ApiModelProperty(value = "支付状态，0：没有支付，1：已经支付")
    private Integer payStatus;

    @TableField("`transaction_id`")
    @ApiModelProperty(value = "事务id")
    private Long transactionId;

    public YzyOrder() {
    }

    @Override
    public String toString() {
        return "YzyOrder{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", buyNum=" + buyNum +
                ", goodId=" + goodId +
                ", userId=" + userId +
                ", payStatus=" + payStatus +
                ", transactionId=" + transactionId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}