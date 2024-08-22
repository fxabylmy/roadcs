package com.jingjin.common.utils;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * ClassName: JavaMailUtils
 * Description: 发送邮箱验证码
 *
 * @Author zjm
 */
@Component
public final class JavaMailUtils {
    private JavaMailUtils(){
    }

    public Boolean sendMessage(String email, String captcha) throws Exception{
        //创建Session对象
        Session session = JavaMailUtils.createSession();
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        message.setSubject("roadcs用户验证码");//设置邮件标题
        message.setText(captcha);//设置邮件内容
        message.setFrom(new InternetAddress("1282431096@qq.com"));//设置发件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));//设置收件人、

        //发送
        Transport.send(message);
        return true;
    }

    public static Session createSession() {
        // 邮箱账号信息
        String userName = "1282431096@qq.com";
        String passWord = "aebwfdrxzhlbffee";

        // SMTP服务器连接信息
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.ssl.enable", "true"); // 启用 SSL
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // 创建Session会话
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, passWord);
            }
        });

        return session;
    }
}
