/*package com.example.ecommerce.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.security.FirebaseConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

@Service
public class OtpService {

    @Autowired
    private FirebaseConfig firebaseConfig;

    public String sendOtp(String phoneNumber) throws Exception {
        firebaseConfig.initialize();

        PhoneAuthProvider.getInstance()
            .verifyPhoneNumber(
                phoneNumber, // Phone number to send OTP
                60, // Timeout in seconds
                TimeUnit.SECONDS,
                null, // Use default verification callbacks
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        // Handle success (OTP sent)
                    }

                    @Override
                    public void onVerificationFailed(Exception e) {
                        // Handle failure
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        // OTP sent successfully, store verificationId for later use
                        // Store this verificationId in session or database
                    }
                });
        return "OTP Sent Successfully!";
    }

    public boolean verifyOtp(String verificationId, String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
        try {
            FirebaseAuth.getInstance().signInWithCredential(credential);
            return true;  // OTP verified successfully
        } catch (Exception e) {
            return false;  // OTP verification failed
        }
    }
}

/**/