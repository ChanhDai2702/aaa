package com.fpt.intern.bestcv.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Properties;

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

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.RecruimentDetail;
import com.fpt.intern.bestcv.entity.RecruimentNews;
import com.fpt.intern.bestcv.entity.Recruiter;

@Service
public class MailService {
	DecimalFormat df = new DecimalFormat("#,###,##0.00");
	@Async
	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.quitwait", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bestcvintern@gmail.com", "nguyentritin");
			}
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("bestcvintern@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
		message.setSubject("Reset Password");

		String msg = "<p>Please confirm your account by clicking this link "
				+ "<a href=\"" + link + "\">here</a></p>"
				+ " or click on the copy the following link on the browser. Please reset the password by clicking: </p>"
				+ "<p><a href=\"" + link + "\"></a></p>" + link;

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html; charset=UTF-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
	}
	@Async
	public void sendEmailOTP(String recipientEmail, String otp,Principal p) throws MessagingException, UnsupportedEncodingException {

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.quitwait", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bestcvintern@gmail.com", "nguyentritin");
			}
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("bestcvintern@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
		message.setSubject("M?? OTP");
		 
		
		String msg = "<p>M?? OTP c???a "+p.getName().toString()+" l?? : </br>" + "<h3>"+otp+"</h3>";
			

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html; charset=UTF-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
	}
	@Async
	public void sendEmailNotificationCadidate(String recipientEmail,Candidate candidate,RecruimentNews news,RecruimentDetail detail) throws MessagingException, UnsupportedEncodingException {

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.quitwait", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bestcvintern@gmail.com", "nguyentritin");
			}
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("bestcvintern@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));	
		
		String mailSubject = "UTV "+candidate.getFullName().toUpperCase()+" ???? N???P ????N ???NG TUY???N";
		message.setSubject(mailSubject);
		
		String mailContent = "<p><b>???ng Tuy???n Vi??n </b>"+candidate.getFullName()+"<b> ???? ???ng Tuy???n V??o V??? Tr??:</b></p>";
		mailContent += "<p><b>T??n V??? Tr??: </b>"+detail.getPosition()+"</p>";
		mailContent += "<p><b>M???c L????ng: </b>"+df.format(detail.getSalary())+"</p>";
		mailContent += "<p><b>Kinh Nghi???m: </b>"+detail.getExperiences()+"</p>";

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(mailContent, "text/html; charset=UTF-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
	}
	@Async
	public void sendEmailNotificationRecruiter(String recipientEmail,Recruiter recruiter,RecruimentNews news,RecruimentDetail detail) throws MessagingException, UnsupportedEncodingException {

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.quitwait", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bestcvintern@gmail.com", "nguyentritin");
			}
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("bestcvintern@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));	
		
		String mailSubject = "Y??U C???U X??T DUY???T CV C???A NH?? TUY???N D???NG "+recruiter.getCompanyName();
		message.setSubject(mailSubject);
		
		String mailContent = "<p><b>Nh?? Tuy???n D???ng </b>"+recruiter.getCompanyName()+"<b> C?? M???t B???n Tin Tuy???n D???ng Ch??? Duy???t</b></p>";
		mailContent += "<p><b>T??n C??ng Vi???c: </b>"+detail.getJob().getJobName()+"</p>";
		mailContent += "<p><b>T??n V??? Tr??: </b>"+detail.getPosition().toString()+"</p>";
		mailContent += "<p><b>M???c L????ng: </b>"+df.format(detail.getSalary())+"</p>";
		mailContent += "<p style=\"color:red;\"><b>Tr???ng Th??i: </b>"+news.getStatus().toString()+"</p>";
		mailContent += "<p><b>Kinh Nghi???m: </b>"+detail.getExperiences().toString()+"</p>";
		mailContent += "<p><a href=''>????y l?? chuy???n trang ph?? duy??t </a></p>";

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(mailContent, "text/html; charset=UTF-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		message.setContent(multipart);
		Transport.send(message);
	}
}
