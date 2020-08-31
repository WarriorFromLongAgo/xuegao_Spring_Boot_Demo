package com.xuegao.springboot_tool.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xuegao.springboot_tool.constant.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.dto
 * <br/> @ClassName：UserInfoDTO
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/9/1 0:01
 */
@Api
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 6814178697496795134L;

    @ApiModelProperty(value = "主键", position = 10)
    private Long id;

    @NotNull(message = "用户账号不能为空")
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    // @ExceptionCode(value = 100001, message = "账号验证错误")
    @ApiModelProperty(value = "姓名", position = 10)
    private String name;

    @ApiModelProperty(value = "登陆用户名", position = 10)
    private String username;

    @ApiModelProperty(value = "登陆密码", position = 10)
    private String password;

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "创建时间", position = 10)
    private Long createTime;

    public UserInfoDTO() {
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