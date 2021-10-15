package com.salemnabeel.email.client;

import com.salemnabeel.email.client.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class EmailClientApplication {

	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {

		SpringApplication.run(EmailClientApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {

		emailSenderService.sendSimpleEmail(
				"", // TODO: put the email address.
				"Welcome Message Without Attachment",
				"Hello From Spring-Boot Email Client."
		);

		emailSenderService.sendEmailWithAttachment(
				"", // TODO: put the email address.
				"Welcome Message With Attachment",
				"Hello From Spring-Boot Email Client.",
				"C:\\Users\\salem-nabeel\\Pictures\\saved-pictures\\backgrounds\\hello-world.png"
		);
	}
}