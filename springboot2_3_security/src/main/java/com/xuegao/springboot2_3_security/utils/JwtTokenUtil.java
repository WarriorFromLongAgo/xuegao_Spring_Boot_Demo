// package com.xuegao.springboot2_3_security.utils;
//
// import com.xuegao.springboot2_3_security.domain.Userinfo;
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
//  * <br/> @author：feijm
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
//     public static String generateToken(Userinfo userinfo) {
//         Map<String, Object> map = new HashMap<>(2);
//         map.put("sub", userinfo.getUsername());
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
//         Userinfo userinfo = getClaimsFromToken(token);
//         username = userinfo.getUsername();
//         return username;
//     }
//
//     public Boolean isTokenExpire(String token) {
//         Userinfo userinfo = getClaimsFromToken(token);
//         username = userinfo.get
//         return username;
//
//
//     }
// }