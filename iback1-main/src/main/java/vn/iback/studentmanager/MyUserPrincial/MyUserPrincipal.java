package vn.iback.studentmanager.MyUserPrincial;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.iback.studentmanager.entity.user;

import java.util.Collection;

public class MyUserPrincipal implements UserDetails {
    private user user;
    public MyUserPrincipal(user user) {
        this.user = user;
    }
    public String getFirstname() {
        return user.getFirstname();
    }
    public String getLastname(){
        return user.getLastname();
    }
    public String getAvatar(){
        return user.getAvatar();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
