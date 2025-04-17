package com.example.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommerce.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OtpController {

    private final Map<String, String> otpStorage = new HashMap<>();

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam("email") String email, HttpSession session) {
        String otp = String.valueOf(new Random().nextInt(999999));
        otpStorage.put(email, otp);
        session.setAttribute("otpEmail", email);
        emailService.sendOtpEmail(email, otp);
        return "otp-verification"; // show page to enter OTP
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam("otp") String userOtp, HttpSession session, Model model) {
        String email = (String) session.getAttribute("otpEmail");
        String storedOtp = otpStorage.get(email);

        if (storedOtp != null && storedOtp.equals(userOtp)) {
            session.setAttribute("verifiedEmail", email);
            otpStorage.remove(email);
            return "redirect:/signup"; // or proceed with account creation
        } else {
            model.addAttribute("error", "Invalid OTP, try again!");
            return "otp-verification";
        }
    }
}
