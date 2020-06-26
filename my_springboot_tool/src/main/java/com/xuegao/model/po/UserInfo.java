package com.xuegao.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xuegao.constant.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.fff.springbootapiseedtest.model.po
 * <br/> @ClassName：UserInfo
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/三3/28 18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8778430056221728L;

    @TableField("id")
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

}