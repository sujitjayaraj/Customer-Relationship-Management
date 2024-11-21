package tech.sujitjayaraj.crm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tech.sujitjayaraj.crm.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                                .requestMatchers("/").hasAnyRole("OWNER", "ADMIN", "EMPLOYEE", "MANAGER")
                                .requestMatchers("/client/**", "/contract/**", "/employeeSearch/**", "/event/**", "/import/**").hasAnyRole("OWNER", "MANAGER", "EMPLOYEE")
                                .requestMatchers("/managerSearch/**").hasAnyRole("OWNER", "MANAGER")
                                .requestMatchers("/admin/**").hasAnyRole("OWNER", "ADMIN"))
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .failureUrl("/login?error=true"))
                .logout(logout -> logout.logoutSuccessUrl("/login"))
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService);

        return httpSecurity.build();
    }
}
