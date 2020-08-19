package com.xuegao.springboot_tool.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：MailUtils
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/19 19:47
 */
public class MailUtils {
    @Resource
    private JavaMailSender javaMailSender;

    public Boolean sendSimpleMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("123@qq.com");
        msg.setTo("123@qq.com");
        msg.setSubject("程序新视界");
        msg.setText("技术分享");
        // 抄送邮箱
        msg.setCc("abc@126.com", "def@126.com");
        javaMailSender.send(msg);
        return true;
    }

    // public void sendHtmlMail() {
    //     String content="<html>\n" +
    //             "<body>\n" +
    //             "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
    //             "</body>\n" +
    //             "</html>";
    //
    //     MimeMessage message = javaMailSender.createMimeMessage();
    //     try {
    //         // 第二个参数true表示需要创建一个multipart message
    //         MimeMessageHelper helper = new MimeMessageHelper(message, true);
    //         helper.setFrom("123@qq.com");
    //         helper.setTo("123@qq.com");
    //         helper.setSubject("程序新视界");
    //         helper.setText(content, true);
    //
    //         javaMailSender.send(message);
    //     } catch (MessagingException e){
    //         System.out.println("发送邮件异常");
    //     }
    // }

}