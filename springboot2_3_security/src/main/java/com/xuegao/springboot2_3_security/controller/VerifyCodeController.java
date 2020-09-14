package com.xuegao.springboot2_3_security.controller;

import com.xuegao.springboot2_3_security.utils.VerifyCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.controller
 * <br/> @ClassName：VerifyCodeController
 * <br/> @Description：http://www.itboyhub.com/2019/1224/springboot-security-login-json.html
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 14:13
 */
@RestController(value = "verify_code")
public class VerifyCodeController {

    // http://localhost:11111/vercode
    @GetMapping("/vercode")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage bufferedImage = verifyCode.getImage();
        String text = verifyCode.getText();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("index_code", text);
        VerifyCode.output(bufferedImage, response.getOutputStream());
    }
}