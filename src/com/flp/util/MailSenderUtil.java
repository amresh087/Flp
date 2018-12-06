package com.flp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.flp.model.User;

@Component
public class MailSenderUtil {

	public static final String USER_VERIFICATION_MAIL = "User Verification Mail";
	public static final String USER_FORGOT_PASSWORD = "Forgot Password";
	public static final String USER_PARENT_LINKING_MAIL = "Student Parent Linking Mail";

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;

	// send mail for verification while end user is registered
	public void sendConfirmationEmail(final User user) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setSubject(USER_VERIFICATION_MAIL);
				message.setTo(user.getEmailId());
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "user-verification.vm", model);
				message.setText(text, true);
			}
		};
		send(preparator);
	}

	// send mail while student want to link up with father
	public void sendParentUserLinkingMail(final User parentObj,
			final String type, final String to) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setSubject(USER_PARENT_LINKING_MAIL);
				message.setTo(to);
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", parentObj);
				String vm = null;
				if (type.equals("alreadyAccount")) {
					vm = "user-parent-linking.vm";
				} else if (type.equals("newAccount")) {
					vm = "parent_registration.vm";
				}
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, vm, model);
				message.setText(text, true);
			}
		};
		send(preparator);
	}

	@Async
	private void send(MimeMessagePreparator preparator) {
		mailSender.send(preparator);

	}

	public void sendForgotPasswordEmail(final User user) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setSubject(USER_FORGOT_PASSWORD);
				message.setTo(user.getEmailId());
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "forgot-password.vm", model);
				message.setText(text, true);
			}
		};
		send(preparator);
		
	}
	
	
	@Async
	public void sendMultipleMails( List<User> toList, String subject, String msg) {
		try {
			
			
			List <String> emailIds =new ArrayList<String>();
			for (User userDetailsForEmailVar:toList){
				
				emailIds.add(userDetailsForEmailVar.getEmailId());
			}
			String [] toStudent = emailIds.toArray(new String[emailIds.size()]);
			//String [] to = {"brijesh.yadav@classteacher.com"};
	       //creating message  
	    SimpleMailMessage message = new SimpleMailMessage();  
	        //message.setFrom(from);
	    message.setBcc(toStudent);
	   // message.setTo(to);//passing array of recipients  
	    message.setSubject(subject);  
	    message.setText(msg);  
	       //sending message  
	    mailSender.send(message);     
	    }
		catch(Exception e){
	    	System.out.println("=============="+e);
	    }
	}  
	
	@Async
	public void singleEmailSender( String strEmailId, String subject, String msg) {
		try {
			
			
			//String [] to = {"brijesh.yadav@classteacher.com"};
	       //creating message  
	    SimpleMailMessage message = new SimpleMailMessage();  
	        //message.setFrom(from);
	    //message.setBcc(toStudent);
	   message.setTo(strEmailId);
	    message.setSubject(subject);  
	    message.setText(msg);  
	       //sending message  
	    mailSender.send(message);     
	    }
		catch(Exception e){
	    	System.out.println("======single========"+e);
	    }
	}  
}
