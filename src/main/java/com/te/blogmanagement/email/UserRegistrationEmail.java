package com.te.blogmanagement.email;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRegistrationEmail {
	@Bean
	public UserRegistrationEmail beanCreationforUserRegistrationEmail() {
		return new UserRegistrationEmail();
	}

	public void sendingEmail(String from, String to, String messageformAdmin) {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("chandramailsender@gmail.com", "gstwigmrsxulqkgt");
			}
		});

		session.setDebug(true);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("User Registration Successfull");
			message.setText(messageformAdmin);
			System.out.println("sending mail");
			Transport.send(message);
			System.out.println("message send successfull");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
