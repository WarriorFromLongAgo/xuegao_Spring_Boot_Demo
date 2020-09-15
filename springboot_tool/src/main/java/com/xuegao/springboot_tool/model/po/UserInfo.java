package com.xuegao.springboot_tool.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xuegao.springboot_tool.constant.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.fff.springbootapiseedtest.model.po
 * <br/> @ClassName：UserInfo
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/三3/28 18:29
 */
@TableName("t_user")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8778430056221728L;

    @TableId("id")
    @ApiModelProperty(value = "主键", position = 10)
    private Long id;

    @TableField("name")
    @ApiModelProperty(value = "姓名", position = 10)
    private String name;

    @TableField("username")
    @ApiModelProperty(value = "登陆用户名", position = 10)
    private String username;

    @TableField("password")
    @ApiModelProperty(value = "登陆密码", position = 10)
    private String password;

    @JsonSerialize(using = LongJsonSerializer.class)
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", position = 10)
    private Long createTime;

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}