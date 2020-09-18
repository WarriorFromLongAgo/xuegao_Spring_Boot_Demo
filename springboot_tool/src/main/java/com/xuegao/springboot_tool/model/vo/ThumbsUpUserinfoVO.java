package com.xuegao.springboot_tool.model.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.vo
 * <br/> @ClassName：ThumbsUpUserinfoVO
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/18 14:41
 */
public class ThumbsUpUserinfoVO implements Serializable {
    private static final long serialVersionUID = 5319821616141457864L;

    @ApiModelProperty(value = "人员id")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "人员介绍信息")
    private String personalIntroduction;

    @ApiModelProperty(value = "人员头像路径")
    private String imgHeadSrc;

    @Override
    public String toString() {
        return "ThumbsUpUserinfoVO{" +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", personalIntroduction='" + personalIntroduction + '\'' +
                ", imgHeadSrc='" + imgHeadSrc + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonalIntroduction() {
        return personalIntroduction;
    }

    public void setPersonalIntroduction(String personalIntroduction) {
        this.personalIntroduction = personalIntroduction;
    }

    public String getImgHeadSrc() {
        return imgHeadSrc;
    }

    public void setImgHeadSrc(String imgHeadSrc) {
        this.imgHeadSrc = imgHeadSrc;
    }
}