package com.xuegao.springboot2_3_security.vscode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.vscode
 * <br/> @ClassName：SecurityTest1
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/25 15:39
 */
@SpringBootTest
public class SecurityTest1 {

    @Test
    public void passwordEncoderTest1() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // $2a$10$blwYsk/MW9DlyeijhYuABuOMWA3vUoo/1qvD1RCpOT.9FaoefKV22
        // $2a$10$8tzlHU1HyjJ2GX3tjO4BReuZWuMDZF2guSqdTkW1juQRvb45r7g0.
        // $2a$10$3ijRTbSQTYlOQ0nNSOScMOuM3g4Cgpv/0u3BmEdS8jJcTw5COd2OW

        // 强散列
        String encode = bCryptPasswordEncoder.encode("123");
        System.out.println(encode);
    }

    @Test
    public void passwordEncoderTest2() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // 强散列
        String encode = bCryptPasswordEncoder.encode("123");
        System.out.println(encode);

        boolean matches = bCryptPasswordEncoder.matches("123", encode);
        System.out.println(matches);
        // $2a$10$JWhJGLEmXH/Cjpb1jNv3PeCQ/SoIcmN7zSayk4qFXlfs4iikvecM6
        // true

        boolean matches2 = bCryptPasswordEncoder.matches("1235", encode);
        System.out.println(matches);
        // false
    }
}