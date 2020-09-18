package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.doo
 * <br/> @ClassName：ThumbsUpUserinfo
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/09/18 11:25
 */
@TableName("t_thumbs_up_userinfo")
public class ThumbsUpUserinfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("主键")
    @ApiModelProperty(value = "主键")
    private Long id;

    @TableField("文章id")
    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @TableField("点赞的用户id")
    @ApiModelProperty(value = "点赞的用户id")
    private Long giveUserId;

    @TableField("被点赞的用户id")
    @ApiModelProperty(value = "被点赞的用户id")
    private Long userId;

    @TableField("点赞状态，0取消，1点赞")
    @ApiModelProperty(value = "点赞状态，0取消，1点赞")
    private String status;

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

    @Override
    public String toString() {
        return "ThumbsUpUserinfo{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", giveUserId=" + giveUserId +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", createId=" + createId +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", updateId=" + updateId +
                ", updateName='" + updateName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGiveUserId() {
        return giveUserId;
    }

    public void setGiveUserId(Long giveUserId) {
        this.giveUserId = giveUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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