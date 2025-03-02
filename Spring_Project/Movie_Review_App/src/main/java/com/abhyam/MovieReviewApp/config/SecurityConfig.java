//package com.abhyam.MovieReviewApp.config;
//
//import com.abhyam.MovieReviewApp.service.MyAdminDetailsService;
//import com.abhyam.MovieReviewApp.service.MyUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @Autowired
//    private MyAdminDetailsService myAdminDetailsService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
////                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/registerUser", "/loginUser", "/loginAdmin", "/registerAdmin").permitAll() // Allow access without authentication
//                        .anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // Default Password Encoder that is no encoder
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        provider.setUserDetailsService(userDetailsService);
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12); // Define PasswordEncoder Bean
//    }
//}

package com.abhyam.MovieReviewApp.config;

import com.abhyam.MovieReviewApp.service.MyAdminDetailsService;
import com.abhyam.MovieReviewApp.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyAdminDetailsService myAdminDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/admin/register", "/admin/login", "/user/login", "/user/register").permitAll() // Allow access without authentication
                        .requestMatchers(HttpMethod.GET, "/movies/**").hasAnyAuthority("USER", "ADMIN") // ✅ Users & Admins can GET movies
                        .requestMatchers(HttpMethod.POST, "/movies/**").hasAuthority("ADMIN") // ✅ Only Admins can add movies
                        .requestMatchers(HttpMethod.PUT, "/movies/**").hasAuthority("ADMIN") // ✅ Only Admins can update movies
                        .requestMatchers(HttpMethod.DELETE, "/movies/**").hasAuthority("ADMIN") // ✅ Only Admins can delete movies
                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // ✅ Protect admin routes
                        .requestMatchers("/user/**").hasAuthority("USER") // ✅ Protect user routes
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * ✅ Separate AuthenticationProvider for Users
     */
    @Bean
    public AuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService); // ✅ Use UserDetailsService for users
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * ✅ Separate AuthenticationProvider for Admins
     */
    @Bean
    public AuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myAdminDetailsService); // ✅ Use UserDetailsService for admins
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * ✅ AuthenticationManager with both authentication providers
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(userAuthenticationProvider(), adminAuthenticationProvider())); // ✅ Support both user and admin authentication
    }

    /**
     * ✅ BCrypt Password Encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
