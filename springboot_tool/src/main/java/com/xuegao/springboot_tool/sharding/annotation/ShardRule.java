// package com.xuegao.springboot_tool.sharding.annotation;
//
// // import com.xuegao.springboot_tool.sharding.shardrule.DefaultShardRule;
//
// import java.lang.annotation.ElementType;
// import java.lang.annotation.Retention;
// import java.lang.annotation.RetentionPolicy;
// import java.lang.annotation.Target;
//
// @Target({ElementType.TYPE})
// @Retention(RetentionPolicy.RUNTIME)
// public @interface ShardRule {
//     String shardKey() default "";
//
//     String shardTable() default "";
//
//     String shardDB() default "";
//
//     Class<?> shardRule() default DefaultShardRule.class;
// }
