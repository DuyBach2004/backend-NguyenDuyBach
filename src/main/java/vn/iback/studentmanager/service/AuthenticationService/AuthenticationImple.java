package vn.iback.studentmanager.service.AuthenticationService;

import ch.qos.logback.core.spi.ErrorCodes;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.iback.studentmanager.dao.InvalidatedTokenRepository;
import vn.iback.studentmanager.entity.user;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
public class AuthenticationImple implements AuthenticationService{
    @Autowired
    InvalidatedTokenRepository invalidatedTokenRepository;
    @NonFinal
    protected static final String SIGNER_KEY="r5mbzjutp8W8f4LyZlVG8bUinp3PpwwAAm4zwAuZvY1ocxcYHyug23MSs5cHGOIp";
    @Override
    public String generateToken(user user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("duybach")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SignedJWT verifyToken(String token) {
        try {
            JWSVerifier verifier = new MACVerifier(getSIGNER_KEY());

            SignedJWT signedJWT = SignedJWT.parse(token);

            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            var verified = signedJWT.verify(verifier);

            if (!verified)
                throw new ApplicationContextException("Token verification failed");

            if (!expiryTime.after(new Date()))
                throw new ApplicationContextException("Token has expired");

            if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
                throw new ApplicationContextException("Token has been invalidated");

            return signedJWT;
        } catch (ParseException e) {
            throw new ApplicationContextException("Failed to parse the token: " + e.getMessage());
        } catch (JOSEException e) {
            throw new ApplicationContextException("Failed to verify the token: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    public String buildScope(user user){
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions()
                            .forEach(permission -> stringJoiner.add(permission.getName()));
            });

        return stringJoiner.toString();
    }

    public String getSIGNER_KEY() {
        return SIGNER_KEY;
    }
    @Override
    public boolean introspect(String token){
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (ApplicationContextException e) {
            isValid = false;
        }

        return isValid;
    }

}
