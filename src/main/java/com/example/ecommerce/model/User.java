package com.example.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String username;

    @Transient
    private String confirmPassword;

    private String aadhaar;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String gender;

    // Getters
    public Long getId() { return id; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    
    public String getUsername() { return username; }
    public String getConfirmPassword() { return confirmPassword; }
    public String getAadhaar() { return aadhaar; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getPincode() { return pincode; }
    public String getGender() { return gender; }
    public LocalDate getDob() { return dob; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    
    public void setUsername(String username) { this.username = username; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    public void setAadhaar(String aadhaar) { this.aadhaar = aadhaar; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    public void setGender(String gender) { this.gender = gender; }
    public void setDob(LocalDate dob) { this.dob = dob; }
}
