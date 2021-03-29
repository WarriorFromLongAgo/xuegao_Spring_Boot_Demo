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
 * <br/> @ClassName：YzyRepo
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/29 18:35:40
 */
@ApiModel(value = "=测试，库存表表")
@TableName("yzy_repo")
public class YzyRepo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    @ApiModelProperty(value = "null")
    private Integer id;

    @TableField("`good_name`")
    @ApiModelProperty(value = "商品名称")
    private String goodName;

    @TableField("`num`")
    @ApiModelProperty(value = "库存数量")
    private Integer num;

    @TableField("`transaction_id`")
    @ApiModelProperty(value = "事务id")
    private Long transactionId;

    public YzyRepo() {
    }

    @Override
    public String toString() {
        return "YzyRepo{" +
                "id=" + id +
                ", goodName='" + goodName + '\'' +
                ", num=" + num +
                ", transactionId=" + transactionId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getGoodName() {
        return goodName;
    }

    public Integer getNum() {
        return num;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}