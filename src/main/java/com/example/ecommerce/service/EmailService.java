package com.example.ecommerce.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        System.out.println("Trying to send OTP to: " + toEmail + " | OTP: " + otp);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("EcomKart - OTP Verification");
            helper.setFrom("rohithmulampaka2998@gmail.com"); // recommended: use domain email

            // Replace this URL with your actual logo later
            String logoUrl = "https://via.placeholder.com/150x50?text=EcomKart+Logo";

            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #eee; border-radius: 10px;'>" +
                        "<div style='text-align: center; margin-bottom: 20px;'>" +
                            "<img src='" + logoUrl + "' alt='EcomKart Logo' style='max-width: 180px; height: auto;' />" +
                        "</div>" +
                        "<h2 style='color: #2c3e50;'>🔐 OTP Verification</h2>" +
                        "<p>Hello,</p>" +
                        "<p>Your one-time password (OTP) for signup is:</p>" +
                        "<h1 style='color: #27ae60; letter-spacing: 5px;'>" + otp + "</h1>" +
                        "<p>This OTP is valid for <strong>5 minutes</strong>. Do not share it with anyone.</p>" +
                        "<br>" +
                        "<p style='font-size: 12px; color: #888;'>If you did not request this, please ignore this email.</p>" +
                        "<hr style='border: none; border-top: 1px solid #ddd; margin: 30px 0;'>" +
                        "<p style='font-size: 14px;'>Thanks,<br><strong>The EcomKart Team</strong></p>" +
                    "</div>";


            helper.setText(htmlContent, true); // true for HTML

            mailSender.send(message);

            System.out.println("✅ OTP email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            System.out.println("❌ Failed to send OTP to " + toEmail + ". Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void sendWelcomeEmail(String toEmail, String username) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(toEmail);
            helper.setSubject("🎉 Welcome to Humanneeds!");
            helper.setFrom("rohithmulampaka2998@gmail.com"); // Use the same one configured in application.properties

            String content = "<h2>Hello " + username + ",</h2>"
                    + "<p>Thank you for registering with <strong>Humanneeds</strong>.</p>"
                    + "<p>We're excited to have you with us. Start exploring the products now!</p>"
                    + "<hr><p>Regards,<br>Team Humanneeds</p>";

            helper.setText(content, true); // Enable HTML
            mailSender.send(mimeMessage);

            System.out.println("✅ Welcome email sent to " + toEmail);
        } catch (Exception e) {
            System.out.println("❌ Failed to send welcome email: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
