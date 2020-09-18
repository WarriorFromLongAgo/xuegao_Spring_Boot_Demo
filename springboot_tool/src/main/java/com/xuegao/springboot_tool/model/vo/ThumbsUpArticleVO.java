package com.xuegao.springboot_tool.model.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.vo
 * <br/> @ClassName：ThumbsUpArticle
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/18 15:00
 */
public class ThumbsUpArticleVO implements Serializable {
    private static final long serialVersionUID = -3601426124117652100L;

    @ApiModelProperty(value = "点赞的文章id")
    private Long articleId;

    @ApiModelProperty(value = "点赞时间")
    private Date thumbsUpTime;

    @Override
    public String toString() {
        return "ThumbsUpArticleVO{" +
                "articleId=" + articleId +
                ", thumbsUpTime=" + thumbsUpTime +
                '}';
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getThumbsUpTime() {
        return thumbsUpTime;
    }

    public void setThumbsUpTime(Date thumbsUpTime) {
        this.thumbsUpTime = thumbsUpTime;
    }
}