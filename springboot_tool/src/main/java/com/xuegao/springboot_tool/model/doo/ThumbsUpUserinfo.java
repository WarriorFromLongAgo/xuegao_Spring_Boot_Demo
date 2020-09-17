package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <br/> @PackageName：com.fff.springbootapiseedtest.model.po
 * <br/> @ClassName：ThumbsUpUserinfo
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/3/28 18:29
 */
@TableName("t_thumbs_up_userinfo")
public class ThumbsUpUserinfo implements Serializable {
    private static final long serialVersionUID = 8009456436984184953L;

    /**
     * 文章id
     */
    private Long id;

    /**
     * 点赞的用户id
     */
    private Long giveUserId;

    /**
     * 被点赞的用户id
     */
    private Long userId;

    /**
     * 点赞状态，0取消，1点赞
     */
    private String status;

    /**
     * 0未删除，1已删除
     */
    private String deleteFlag;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 创建人真实名称
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人id
     */
    private Integer updateId;

    /**
     * 修改人真实名称
     */
    private String updateName;

    /**
     * 修改时间
     */
    private Date updateTime;

    public ThumbsUpUserinfo() {
    }

    @Override
    public String toString() {
        return "ThumbsUpUserinfo{" +
                "id=" + id +
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
