package com.thanhpl.helper.mail;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GmailHelper {

	private String username;
	private String password;

	public GmailHelper(String username, String password) {
		this.username = username;
		this.password = password;
	}

	private Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		return session;
	}

	private boolean isEmailFormat(String email) {
		if (email.length() < 201) {
			Pattern pattern = Pattern.compile(
					"^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+([;.](([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+)*$");
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		} else {
			return false;
		}
	}

	public void sendSimpleMail(String from, String to, String subject, String content) throws Exception {
		if (!isEmailFormat(from)) {
			throw new Exception("From email address is invalid");
		}
		if (!isEmailFormat(to)) {
			throw new Exception("To email address is invalid");
		}

		try {
			Session session = getSession();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(content);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendHtmlMail(String from, String to, String subject, String htmlContent) throws Exception {
		if (!isEmailFormat(from)) {
			throw new Exception("From email address is invalid");
		}
		if (!isEmailFormat(to)) {
			throw new Exception("To email address is invalid");
		}
		
		try {
			Session session = getSession();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(htmlContent, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendAttachmentMail(String from, String to, String subject, String htmlContent, List<File> files)
			throws Exception {
		if (!isEmailFormat(from)) {
			throw new Exception("From email address is invalid");
		}
		if (!isEmailFormat(to)) {
			throw new Exception("To email address is invalid");
		}
		
		try {
			Session session = getSession();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(htmlContent, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			if (files != null && files.size() > 0) {
				int size = files.size();
				for (int i = 0; i < size; i++) {
					MimeBodyPart fileBodyPart = new MimeBodyPart();
					fileBodyPart.attachFile(files.get(i));
					multipart.addBodyPart(fileBodyPart);
				}
			}

			message.setContent(multipart);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
