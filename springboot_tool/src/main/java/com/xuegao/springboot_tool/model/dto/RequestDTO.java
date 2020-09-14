package com.xuegao.springboot_tool.model.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.model.dto
 * <br/> @ClassName：RequestDTO
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/9 11:12
 */
public class RequestDTO {

    @ApiModelProperty(value = "来源", position = 10)
    private String source;

    @ApiModelProperty(value = "去向", position = 10)
    private String target;

    @ApiModelProperty(value = "值", position = 10)
    private Object value;

    public RequestDTO() {
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", value=" + value +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}