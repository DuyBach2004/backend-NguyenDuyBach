package vn.iback.studentmanager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.iback.studentmanager.service.Userservice;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    protected String signerKey="r5mbzjutp8W8f4LyZlVG8bUinp3PpwwAAm4zwAuZvY1ocxcYHyug23MSs5cHGOIp";

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
//                        .requestMatchers("/css/**","/image/**").permitAll()
                        .requestMatchers("/auth/token").permitAll()
                        .requestMatchers("/user").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST,"/user/comment").hasAuthority("ROLE_ROLE_USER")
                        .requestMatchers(HttpMethod.POST,"/user/reply").hasAuthority("ROLE_ROLE_USER")
                        .requestMatchers("/user/post").permitAll()
                        .requestMatchers("/user/role").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/user/lophocphandadangki").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/thoi-gian-hoc/**").hasAuthority("ROLE_ROLE_ADMIN")
//                        .requestMatchers("/register/**").permitAll()
//                        .requestMatchers("/home/**").authenticated()
////                        .requestMatchers("/homeUser/**").hasRole("USER")
//                        .requestMatchers("/home/**").hasRole("ADMIN")
//                        .requestMatchers("/lich-hoc-user/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,"/lich-hoc/ma-lich-hoc").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/lich-hoc/ma-lich-hoc").permitAll()
                        .requestMatchers("/lop/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/diem/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/diem/ma-bang-diem").permitAll()
//                        .requestMatchers("/notebook/**").hasRole("ADMIN")
                        .requestMatchers("/class/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/user/hocphan/create").hasAuthority("ROLE_ROLE_USER")
                        .requestMatchers("/hocKy/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/ketquahoctap/ma-ket-qua-hoc-tap").permitAll()
                        .requestMatchers("/ketquahoctap/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/ketquahoctap/ma-ket-qua-hoc-tap").permitAll()
                        .requestMatchers("/khoa/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/lophocphan/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/lophocphan").permitAll()
                        .requestMatchers("/specialization/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/subject/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/teacher/**").hasAuthority("ROLE_ROLE_ADMIN")
//                        .requestMatchers("/thoi-gian-hoc/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
        );
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer ->
                                jwtConfigurer.decoder(jwtDecoder()))
        );
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}
