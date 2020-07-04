package com.mail;

import com.pwpew.utils.Mail;
import com.pwpew.utils.MailUtils;
import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;

/**
 * @author miaoyin
 * @date 2020/5/29 - 12:09
 * @commet:
 */
public class mailTest {

    @Test
    public void test1(){

        try {
            Session session = MailUtils.createSession("smtp.163.com","sdauuse","AQOBPEWJSWAISLRP");
            Mail mail = new Mail("sdauuse@163.com","751648872@qq.com","小心标题","邮件正文：验证消息");
            MailUtils.send(session,mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
