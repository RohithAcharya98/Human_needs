# 🛒 Human Needs – Spring Boot E-Commerce Platform

A full-featured, modern e-commerce application built with Spring Boot 3.x, Thymeleaf, Java, HTML, CSS, and JavaScript. It features user authentication, role-based access, product browsing, cart management, and secure checkout.

---

## 🚀 Features

- 🔐 User Signup & Login with Role-based Access
- 🛍️ Product Listings with Ratings, Deals, & Badges
- 🛒 Cart with "Save for Later", "Move to Cart" Options
- 📦 Order Placement & Checkout Integration (Razorpay)
- 📧 Email Notifications for Order and Cart Events
- 📱 OTP-based Verification (Email / SMS)
- 🌗 Dark Mode UI Toggle
- 💬 Chatbot Integration with Chat Logging
- 📊 Admin Dashboard with Product Management
- 📦 Docker-Ready for Deployment

---

## 🧱 Tech Stack

| Layer        | Tech Used                            |
|--------------|---------------------------------------|
| Backend      | Spring Boot 3.x, Spring Security      |
| Frontend     | Thymeleaf, HTML, CSS, JavaScript      |
| Database     | MySQL                                 |
| Payment      | Razorpay                              |
| Email/OTP    | SMTP, Fast2SMS/Twilio (optional)       |
| Version Ctrl | Git + GitHub                          |
| Deployment   | Docker + VPS (Coming Soon)            |

---

## 📁 Project Structure

```bash
Human_needs/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.humanneeds/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── config/
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
