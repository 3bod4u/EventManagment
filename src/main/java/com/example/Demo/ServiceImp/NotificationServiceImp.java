package com.example.Demo.ServiceImp;


import com.example.Demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImp {

	private JavaMailSender javaMailSender;

	@Autowired
	public NotificationServiceImp(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotificaitoin(User user,String subject, String text) throws MailException {
		// send email
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("aharbi1020@gmail.com");
		mail.setSubject(subject);
		mail.setText(text);
		
		javaMailSender.send(mail);
	}
	
}
