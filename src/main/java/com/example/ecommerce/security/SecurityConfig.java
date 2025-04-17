package com.example.ecommerce.security; // Use your actual package

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf
	            .ignoringRequestMatchers("/logout", "/send-otp", "/verify-otp", "/api/chat") // ✅ ignore CSRF for chat too
	        )
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/chat").permitAll() // ✅ must be FIRST
	            .requestMatchers("/", "/index", "/index.html", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
	            .requestMatchers("/signup", "/register", "/signup/**").permitAll()
	            .requestMatchers(HttpMethod.GET, "/signup").permitAll()
	            .requestMatchers(HttpMethod.POST, "/signup").permitAll()
	            .requestMatchers("/send-otp", "/verify-otp").permitAll()
	            .requestMatchers("/add-to-cart", "/cart", "/checkout").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .defaultSuccessUrl("/", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout")
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .deleteCookies("JSESSIONID")
	            .permitAll()
	        )
	        .sessionManagement(session -> session
	            .invalidSessionUrl("/login?error=sessionExpired")
	            .maximumSessions(1)
	            .expiredUrl("/login?error=sessionExpired")
	        );

	    return http.build();
	}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for secure password encoding
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}
