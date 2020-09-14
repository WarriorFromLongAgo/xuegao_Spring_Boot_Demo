package com.xuegao.springboot2_3_security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config
 * <br/> @ClassName：SecurityConfig7
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 16:09
 */
// public class SecurityConfig7 extends WebSecurityConfigurerAdapter {
//
//     private final Logger log = LoggerFactory.getLogger(getClass());
//
//     @Override
//     protected void configure(HttpSecurity httpSecurity) throws Exception {
//         // /db/** 格式的路径需要具备 dba 角色才能访问，
//         // /admin/** 格式的路径则需要具备 admin 角色才能访问，
//         // /user/** 格式的路径，则需要具备 user 角色才能访问，
//         // 此时提供相关接口，会发现，dba 除了访问 /db/**，也能访问 /admin/** 和 /user/** ，
//         // admin 角色除了访问 /admin/** ，也能访问 /user/**，
//         // user 角色则只能访问 /user/**。
//         httpSecurity.authorizeRequests()
//                 .antMatchers("/admin/**")
//                 .hasRole("admin")
//                 .antMatchers("/db/**")
//                 .hasRole("dba")
//                 .antMatchers("/user/**")
//                 .hasRole("user")
//                 .and()
//                 .formLogin()
//                 .loginProcessingUrl("/doLogin")
//                 .permitAll()
//                 .and()
//                 .csrf()
//                 .disable();
//     }
//
//     /**
//      * <br/> @Title:
//      * <br/> @MethodName:  roleHierarchy
//      * <br/>
//      * <br/> @Return org.springframework.security.access.hierarchicalroles.RoleHierarchy
//      * <br/> @Description: 在这里我们提供了一个 RoleHierarchy 接口的实例，使用字符串来描述了角色之间的继承关系，
//      * <br/> @Description: ROLE_dba 具备 ROLE_admin 的所有权限，而 ROLE_admin 则具备 ROLE_user 的所有权限，继承与继承之间用一个空格隔开。
//      * <br/> @Description: 提供了这个 Bean 之后，以后所有具备 ROLE_user 角色才能访问的资源，
//      * <br/> @Description: ROLE_dba 和 ROLE_admin 也都能访问，具备 ROLE_amdin 角色才能访问的资源， ROLE_dba 也能访问。
//      * <br/> @author: xuegao
//      * <br/> @date:  2020/6/18 16:10
//      */
//     @Bean
//     RoleHierarchy roleHierarchy() {
//         RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//         String hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
//         roleHierarchy.setHierarchy(hierarchy);
//         return roleHierarchy;
//     }

    // 2.1 之前
    // private void buildRolesReachableInOneStepMap() {
    //     Pattern pattern = Pattern.compile("(\\s*([^\\s>]+)\\s*>\\s*([^\\s>]+))");
    //     Matcher roleHierarchyMatcher = pattern.matcher(this.roleHierarchyStringRepresentation);
    //     this.rolesReachableInOneStepMap = new HashMap<GrantedAuthority, Set<GrantedAuthority>>();
    //     while (roleHierarchyMatcher.find()) {
    //         GrantedAuthority higherRole = new SimpleGrantedAuthority(roleHierarchyMatcher.group(2));
    //         GrantedAuthority lowerRole = new SimpleGrantedAuthority(roleHierarchyMatcher.group(3));
    //         Set<GrantedAuthority> rolesReachableInOneStepSet;
    //         if (!this.rolesReachableInOneStepMap.containsKey(higherRole)) {
    //             rolesReachableInOneStepSet = new HashSet<>();
    //             this.rolesReachableInOneStepMap.put(higherRole, rolesReachableInOneStepSet);
    //         } else {
    //             rolesReachableInOneStepSet = this.rolesReachableInOneStepMap.get(higherRole);
    //         }
    //         addReachableRoles(rolesReachableInOneStepSet, lowerRole);
    //         log.debug("buildRolesReachableInOneStepMap() - From role " + higherRole
    //                 + " one can reach role " + lowerRole + " in one step.");
    //     }
    // }
    //
    // // 2.1 之后
    // private void buildRolesReachableInOneStepMap() {
    //     this.rolesReachableInOneStepMap = new HashMap<GrantedAuthority, Set<GrantedAuthority>>();
    //     try (BufferedReader bufferedReader = new BufferedReader(
    //             new StringReader(this.roleHierarchyStringRepresentation))) {
    //         for (String readLine; (readLine = bufferedReader.readLine()) != null;) {
    //             String[] roles = readLine.split(" > ");
    //             for (int i = 1; i < roles.length; i++) {
    //                 GrantedAuthority higherRole = new SimpleGrantedAuthority(
    //                         roles[i - 1].replaceAll("^\\s+|\\s+$", ""));
    //                 GrantedAuthority lowerRole = new SimpleGrantedAuthority(roles[i].replaceAll("^\\s+|\\s+$ Set<GrantedAuthority> rolesReachableInOneStepSet;
    //                 if (!this.rolesReachableInOneStepMap.containsKey(higherRole)) {
    //                     rolesReachableInOneStepSet = new HashSet<GrantedAuthority>();
    //                     this.rolesReachableInOneStepMap.put(higherRole, rolesReachableInOneStepSet);
    //                 } else {
    //                     rolesReachableInOneStepSet = this.rolesReachableInOneStepMap.get(higherRole);
    //                 }
    //                 addReachableRoles(rolesReachableInOneStepSet, lowerRole);
    //                 if (log.isDebugEnabled()) {
    //                     log.debug("buildRolesReachableInOneStepMap() - From role " + higherRole
    //                             + " one can reach role " + lowerRole + " in one step.");
    //                 }
    //             }
    //         }
    //     } catch (IOException e) {
    //         throw new IllegalStateException(e);
    //     }
    // }
// }