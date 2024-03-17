package vn.iback.studentmanager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.iback.studentmanager.service.Userservice;

import java.io.IOException;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    //ma hoa mat khau
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(Userservice userservice){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userservice);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatar/**")
                .addResourceLocations("file:/D:/Java/studentmanager/avatar/");
    }
    //vô hiệu hóa SerializationFeature.FAIL_ON_EMPTY_BEANS
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer->configurer
                        .requestMatchers("/css/**","/image/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/home/**").authenticated()
//                        .requestMatchers("/homeUser/**").hasRole("USER")
                        .requestMatchers("/home/**").hasRole("ADMIN")
                        .requestMatchers("/lich-hoc-user/**").hasRole("USER")
                        .requestMatchers("/lich-hoc/**").hasRole("ADMIN")
                        .requestMatchers("/class/**").hasRole("ADMIN")
                        .requestMatchers("/diem/**").hasRole("ADMIN")
                        .requestMatchers("/notebook/**").hasRole("ADMIN")
                        .requestMatchers("/diem-user/**").hasRole("USER")
                        .requestMatchers("/hocKy/**").hasRole("ADMIN")
                        .requestMatchers("/ketquahoctap/**").hasRole("ADMIN")
                        .requestMatchers("/ketquahoctap/**").hasRole("ADMIN")
                        .requestMatchers("/khoa/**").hasRole("ADMIN")
                        .requestMatchers("/lop/**").hasRole("ADMIN")
                        .requestMatchers("/specialization/**").hasRole("ADMIN")
                        .requestMatchers("/subject/**").hasRole("ADMIN")
                        .requestMatchers("/teacher/**").hasRole("ADMIN")
                        .requestMatchers("/thoi-gian-hoc/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
//                        .anyRequest().authenticated()
        )
                .formLogin(
                form->form.loginPage("/showLoginPage")
//                        .loginPage("/showLoginPage")
//                        .loginProcessingUrl("/authenticateTheUser")
//                        .successHandler((request, response, authentication) -> {
//                            boolean isUser = authentication.getAuthorities().stream()
//                                    .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
//                            if (isUser) {
//                                response.sendRedirect("/homeUser");
//                            } else {
//                                response.sendRedirect("/home/showHome");
//                            }
//                        })
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                                Authentication authentication) throws IOException, ServletException {
                                boolean isUser = authentication.getAuthorities().stream()
                                        .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
                                if (isUser) {
                                    User user = (User) authentication.getPrincipal();
                                    System.out.println(user.getUsername());
                                    request.getSession().setAttribute("user", user);
                                    response.sendRedirect("/homeUser");
                                } else {
                                    response.sendRedirect("/home/showHome");
                                }
                            }
                        })
                        .permitAll()
        ).logout(
                logout->logout.permitAll()
        ).exceptionHandling(
                configurer->configurer.accessDeniedPage("/showPage403")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf->csrf.disable());
        return http.build();
    }


}
