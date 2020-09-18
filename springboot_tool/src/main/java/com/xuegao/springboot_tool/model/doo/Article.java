package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.doo
 * <br/> @ClassName：Article
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/09/18 13:47
 */
@TableName("t_article")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("文章id")
    @ApiModelProperty(value = "文章id")
    private Long id;

    @TableField("文章标题")
    @ApiModelProperty(value = "文章标题")
    private String title;

    @TableField("文章的正文")
    @ApiModelProperty(value = "文章的正文")
    private String text;

    @TableField("0未删除，1已删除")
    @ApiModelProperty(value = "0未删除，1已删除")
    private String deleteFlag;

    @TableField("创建人id")
    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @TableField("创建人真实名称")
    @ApiModelProperty(value = "创建人真实名称")
    private String createName;

    @TableField("创建时间")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField("修改人id")
    @ApiModelProperty(value = "修改人id")
    private Integer updateId;

    @TableField("修改人真实名称")
    @ApiModelProperty(value = "修改人真实名称")
    private String updateName;

    @TableField("修改时间")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
