package vn.iback.studentmanager.security;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iback.studentmanager.dao.RoleRespository;
import vn.iback.studentmanager.dao.UserRespository;
import vn.iback.studentmanager.entity.roles;
import vn.iback.studentmanager.entity.user;
import org.slf4j.LoggerFactory;


import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    private static final Logger log = LoggerFactory.getLogger(user.class);

    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRespository roleRespository;

    @Bean
    ApplicationRunner applicationRunner(UserRespository userRepository){
        return args -> {
            if (!userRepository.existsById("admin")){
//                var roles = new HashSet<String>();
                vn.iback.studentmanager.entity.roles defautRole=roleRespository.findByName("ROLE_ADMIN");
//                roles.add(defautRole);

                user user = vn.iback.studentmanager.entity.user.createAdmin(passwordEncoder,defautRole);
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
