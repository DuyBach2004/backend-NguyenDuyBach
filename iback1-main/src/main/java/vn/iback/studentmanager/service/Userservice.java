package vn.iback.studentmanager.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.iback.studentmanager.entity.user;

import java.util.List;

public interface Userservice extends UserDetailsService {
    public user findByUsername(String username);
    public void save(user user);
    public void update(user user);
    public List<user> findAll();
    public void deleteByUsername(String userName);
}
