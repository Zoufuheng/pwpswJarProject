package com.pwpew.utils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.pwpew.utils.AttachBean;
import com.pwpew.utils.Mail;

public class MailUtils {
	public static Session createSession(String host, final String username, final String password) {
		Properties prop = new Properties();
		prop.setProperty("mail.host", host);// 鎸囧畾涓绘満
		prop.setProperty("mail.smtp.auth", "true");// 鎸囧畾楠岃瘉涓簍rue

		// 鍒涘缓楠岃瘉鍣�
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		// 鑾峰彇session瀵硅薄
		return Session.getInstance(prop, auth);
	}

	/**
	 * 鍙戦�佹寚瀹氱殑閭欢
	 * 
	 * @param mail
	 */
	public static void send(Session session, final Mail mail) throws MessagingException, IOException {

		MimeMessage msg = new MimeMessage(session);// 鍒涘缓閭欢瀵硅薄
		msg.setFrom(new InternetAddress(mail.getFrom()));// 璁剧疆鍙戜欢浜�
		msg.addRecipients(RecipientType.TO, mail.getToAddress());// 璁剧疆鏀朵欢浜�

		// 璁剧疆鎶勯��
		String cc = mail.getCcAddress();
		if (!cc.isEmpty()) {
			msg.addRecipients(RecipientType.CC, cc);
		}

		// 璁剧疆鏆楅��
		String bcc = mail.getBccAddress();
		if (!bcc.isEmpty()) {
			msg.addRecipients(RecipientType.BCC, bcc);
		}

		msg.setSubject(mail.getSubject());// 璁剧疆涓婚

		MimeMultipart parts = new MimeMultipart();// 鍒涘缓閮ㄤ欢闆嗗璞�

		MimeBodyPart part = new MimeBodyPart();// 鍒涘缓涓�涓儴浠�
		part.setContent(mail.getContent(), "text/html;charset=utf-8");// 璁剧疆閭欢鏂囨湰鍐呭
		parts.addBodyPart(part);// 鎶婇儴浠舵坊鍔犲埌閮ㄤ欢闆嗕腑

		///////////////////////////////////////////

		// 娣诲姞闄勪欢
		List<AttachBean> attachBeanList = mail.getAttachs();// 鑾峰彇鎵�鏈夐檮浠�
		if (attachBeanList != null) {
			for (AttachBean attach : attachBeanList) {
				MimeBodyPart attachPart = new MimeBodyPart();// 鍒涘缓涓�涓儴浠�
				attachPart.attachFile(attach.getFile());// 璁剧疆闄勪欢鏂囦欢
				attachPart.setFileName(MimeUtility.encodeText(attach.getFileName()));// 璁剧疆闄勪欢鏂囦欢鍚�
				String cid = attach.getCid();
				if (cid != null) {
					attachPart.setContentID(cid);
				}
				parts.addBodyPart(attachPart);
			}
		}

		msg.setContent(parts);// 缁欓偖浠惰缃唴瀹�
		Transport.send(msg);// 鍙戦偖浠�
	}
}
