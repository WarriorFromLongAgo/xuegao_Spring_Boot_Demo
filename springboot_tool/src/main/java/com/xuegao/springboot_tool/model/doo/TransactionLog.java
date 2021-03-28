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
 * <br/> @ClassName：TransactionLog
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/29 00:00:05
 */
@ApiModel(value = "null")
@TableName("mq_transaction_log")
public class TransactionLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "null")
    private Long id;

    @TableField("from_user_id")
    @ApiModelProperty(value = "转账人id")
    private Long fromUserId;

    @TableField("change_money")
    @ApiModelProperty(value = "转账金额")
    private Long changeMoney;

    @TableField("transaction_id")
    @ApiModelProperty(value = "消息事务id")
    private String transactionId;

    @TableField("to_user_id")
    @ApiModelProperty(value = "被转账人id")
    private Long toUserId;

    @TableField("record_no")
    @ApiModelProperty(value = "转账流水编号")
    private String recordNo;

    public TransactionLog() {
    }

    public Long getId() {
        return id;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public Long getChangeMoney() {
        return changeMoney;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setChangeMoney(Long changeMoney) {
        this.changeMoney = changeMoney;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    @Override
    public String toString() {
        return "TransactionLog { " +
            "id=" + id +
            ", fromUserId=" + fromUserId +
            ", changeMoney=" + changeMoney +
            ", transactionId=" + transactionId +
            ", toUserId=" + toUserId +
            ", recordNo=" + recordNo +
            "}";
    }
}