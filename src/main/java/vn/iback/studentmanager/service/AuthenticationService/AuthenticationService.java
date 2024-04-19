package vn.iback.studentmanager.service.AuthenticationService;

import com.nimbusds.jwt.SignedJWT;
import vn.iback.studentmanager.entity.user;

public interface AuthenticationService {
    public String generateToken(user user);
    public SignedJWT verifyToken(String token);
    public String buildScope(user user);
    public boolean introspect(String token);
}
