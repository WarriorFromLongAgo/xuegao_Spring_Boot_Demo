package com.xuegao.websocket.model.po;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.fff.springbootapiseedtest.model.po
 * <br/> @ClassName：UserInfo
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/三3/28 18:29
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8778430056221728L;

    @ApiModelProperty(value = "主键", position = 10)
    private Long id;

    @ApiModelProperty(value = "姓名", position = 10)
    private String name;

    @ApiModelProperty(value = "登陆用户名", position = 10)
    private String username;

    @ApiModelProperty(value = "登陆密码", position = 10)
    private String password;

    @ApiModelProperty(value = "创建时间", position = 10)
    private Long createTime;

    public UserInfo() {
    }

    public UserInfo(Long id, String name, String username, String password, Long createTime) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
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