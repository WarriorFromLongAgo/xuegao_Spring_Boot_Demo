// package com.xuegao.springboot2_3_security.utils;
//
// import com.xuegao.springboot2_3_security.domain.SysUserinfo;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.stereotype.Component;
//
// import java.security.Signature;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot2_3_security.utils
//  * <br/> @ClassName：JwtTokenUtil
//  * <br/> @Description：
//  * <br/> @author：xuegao
//  * <br/> @date：2020/7/30 0:23
//  */
// @Component
// public class JwtTokenUtil {
//
//     private static Long expiration = 1313L;
//
//     private static String secret = "0";
//
//     private String header = "dad";
//
//     public static String generateToken(SysUserinfo SysUserinfo) {
//         Map<String, Object> map = new HashMap<>(2);
//         map.put("sub", SysUserinfo.getUsername());
//         map.put("create", new Date());
//         return generateToken(map);
//         ;
//     }
//
//     public static String generateToken(Map<String, Object> map) {
//         Date expireDate = new Date(System.currentTimeMillis() + expiration);
//
//         return Jwts.builder().setClaims(map).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, secret).compact();
//     }
//
//     public String getUsernameFromToken(String token) {
//         String username;
//
//         SysUserinfo SysUserinfo = getClaimsFromToken(token);
//         username = SysUserinfo.getUsername();
//         return username;
//     }
//
//     public Boolean isTokenExpire(String token) {
//         SysUserinfo SysUserinfo = getClaimsFromToken(token);
//         username = SysUserinfo.get
//         return username;
//
//
//     }
// }