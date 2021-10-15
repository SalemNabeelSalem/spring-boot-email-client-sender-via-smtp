package com.salemnabeel.email.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(""); // TODO: put your email address.

        message.setTo(toEmail);

        message.setSubject(subject);

        message.setText(body);

        mailSender.send(message);

        System.out.println("Mail Send...");
    }

    public void sendEmailWithAttachment(String toEmail, String subject,
                                        String body, String attachment) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(""); // TODO: put your email address.

        mimeMessageHelper.setTo(toEmail);

        mimeMessageHelper.setText(body);

        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystem.getFilename()), fileSystem);

        mailSender.send(mimeMessage);

        System.out.println("Mail Send...");
    }
}