package com.xuegao.springboot_tool.model.doo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.doo
 * <br/> @ClassName：Jvm
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/9/9 10:50
 */
@TableName("t_my_jvm")
public class MyJvm {

    @TableId
    @ApiModelProperty(value = "订单id", position = 10)
    private Long id;

    @TableField("configuration")
    @ApiModelProperty(value = "配置", position = 10)
    private String configuration;

    @TableField("description")
    @ApiModelProperty(value = "事情的描述", position = 10)
    private String description;

    public MyJvm() {
    }

    @Override
    public String toString() {
        return "MyJvm{" +
                "id=" + id +
                ", configuration='" + configuration + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}