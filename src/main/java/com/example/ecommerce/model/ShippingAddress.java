package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}

	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}

	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}

	public String getState() {return state;}
	public void setState(String state) {this.state = state;}

	public String getCity() {return city;}
	public void setCity(String city) {this.city = city;}

	public String getPincode() {return pincode;}
	public void setPincode(String pincode) {this.pincode = pincode;}
	
	public void setUser(User user) {this.user=user;}
}

