package com.pwpew.utils;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.util.Random;

/**
 * @author miaoyin
 * @date 2020/5/14 - 12:12
 * @commet:
 */
public class CommonUtil {

    public static int getWordCount(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;

        }
        return length;

    }


    public static String random() {
        //用时间来作为种子
        long t = System.currentTimeMillis();

        Random r = new Random(t);
        String number="";
        for (int i = 0; i < 5; i++) {
            int ran1 = r.nextInt(100);
            number = number + String.valueOf(ran1);
        }

        return number;
    }

    public static void sendMail(String to, String content) {
        try {
            Session session = MailUtils.createSession("smtp.163.com", "sdauuse", "AQOBPEWJSWAISLRP");
            Mail mail = new Mail("sdauuse@163.com", to, "来自公益寻人网站的验证码", "您的验证码为:" + content);//to为发给谁，content为正文内容，用于传输验证码
            MailUtils.send(session, mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
