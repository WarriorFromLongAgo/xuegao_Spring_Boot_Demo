package com.xuegao.springboot2_3_kafka.domain.vo;

import lombok.*;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_kafka.domain.vo
 * <br/> @ClassName：
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/12 10:59
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {

    private Integer id;

    private String userName;

    private Integer age;

}