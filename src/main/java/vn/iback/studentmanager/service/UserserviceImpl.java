package vn.iback.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.RoleRespository;
import vn.iback.studentmanager.dao.UserRespository;
import vn.iback.studentmanager.entity.roles;
import vn.iback.studentmanager.entity.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserserviceImpl implements Userservice {
    private UserRespository userRespository;
    private RoleRespository roleRespository;
    @Autowired
    public UserserviceImpl(UserRespository userRespository, RoleRespository roleRespository) {
        this.userRespository = userRespository;
        this.roleRespository = roleRespository;
    }

    @Override
    public user findByUsername(String username) {
        return userRespository.findByUsername(username);
    }

    @Override
    public void save(user user) {
        userRespository.save(user);
    }

    @Override
    public void update(user user) {
        userRespository.saveAndFlush(user);
    }

    @Override
    public List<user> findAll() {
        return userRespository.findAll();
    }

    @Override
    public void deleteByUsername(String userName) {
        userRespository.deleteById(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user_1=userRespository.findByUsername(username);
        if(user_1==null){
            throw new UsernameNotFoundException("tài khoản không tồn tại");
        }

        return new User(user_1.getUsername(),user_1.getPassword(),rolesToAuthorities(user_1.getRoles()));
    }
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<roles> roles){
        return  roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
