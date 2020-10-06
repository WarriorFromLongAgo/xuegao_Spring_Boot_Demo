package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.doo
 * <br/> @ClassName：Dic
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/09/28 17:13
 */
@ApiModel(value = "字典表")
@TableName("t_dic")
public class Dic implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @TableField("pid")
    @ApiModelProperty(value = "0不启用，1草稿，2启用")
    private Integer pid;

    @TableField("type_id")
    @ApiModelProperty(value = "区分某一类数据")
    private Integer typeId;

    @TableField("value")
    @ApiModelProperty(value = "值")
    private String value;

    @TableField("name")
    @ApiModelProperty(value = "名称")
    private String name;

    @TableField("description")
    @ApiModelProperty(value = "描述")
    private String description;

    @TableField("status")
    @ApiModelProperty(value = "0不启用，1草稿，2启用")
    private Integer status;

    @TableField("double_ee")
    @ApiModelProperty(value = "null")
    private Double doubleEe;

    @TableField("delete_flag")
    @ApiModelProperty(value = "0未删除，1已删除")
    private Integer deleteFlag;

    @TableField("create_id")
    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @TableField("create_name")
    @ApiModelProperty(value = "创建人真实名称")
    private String createName;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField("update_id")
    @ApiModelProperty(value = "修改人id")
    private Long updateId;

    @TableField("update_name")
    @ApiModelProperty(value = "修改人真实名称")
    private String updateName;

    @TableField("update_time")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public Integer getPid() {
        return pid;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatus() {
        return status;
    }

    public Double getDoubleEe() {
        return doubleEe;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public Long getCreateId() {
        return createId;
    }

    public String getCreateName() {
        return createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribe(String description) {
        this.description = description;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDoubleEe(Double doubleEe) {
        this.doubleEe = doubleEe;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Dic{" +
                "id=" + id +
                ", pid=" + pid +
                ", typeId=" + typeId +
                ", value=" + value +
                ", name=" + name +
                ", description=" + description +
                ", status=" + status +
                ", doubleEe=" + doubleEe +
                ", deleteFlag=" + deleteFlag +
                ", createId=" + createId +
                ", createName=" + createName +
                ", createTime=" + createTime +
                ", updateId=" + updateId +
                ", updateName=" + updateName +
                ", updateTime=" + updateTime +
                "}";
    }
}
