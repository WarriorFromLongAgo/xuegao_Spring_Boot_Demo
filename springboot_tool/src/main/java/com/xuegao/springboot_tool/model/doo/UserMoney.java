package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <br/> @PackageName：com.sf.edu.domain.doo.train
 * <br/> @ClassName：UserMoney
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/29 00:01:11
 */
@ApiModel(value = "null")
@TableName("mq_user_money")
public class UserMoney implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    @ApiModelProperty(value = "null")
    private Long id;

    @TableField("`user_id`")
    @ApiModelProperty(value = "转账人id")
    private Long userId;

    @TableField("`money`")
    @ApiModelProperty(value = "金额")
    private Long money;

    public UserMoney() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMoney() {
        return money;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserMoney { " +
            "id=" + id +
            ", userId=" + userId +
            ", money=" + money +
            "}";
    }
}