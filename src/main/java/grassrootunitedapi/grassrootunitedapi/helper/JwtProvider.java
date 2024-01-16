package grassrootunitedapi.grassrootunitedapi.helper;

import grassrootunitedapi.grassrootunitedapi.constant.GlobalConstant;
import grassrootunitedapi.grassrootunitedapi.entity.UserInformation;
import grassrootunitedapi.grassrootunitedapi.model.general.TokenPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserInformation user) {
        long expiredToken = System.currentTimeMillis() + (1000 * 60 * 60 * 8);
        Date expiredDate = new Date(expiredToken);

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", user.getId());
        payload.put("userType", user.getUserType());
        payload.put("email", user.getEmail());
        payload.put("firstName", user.getFirstName());
        payload.put("lastName", user.getLastName());

        return Jwts.builder()
                .setClaims(payload)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public TokenPayload validateBackOfficeToken(String token) {
        TokenPayload tokenPayload = validateToken(token);

        if (tokenPayload.getUserType().equals(GlobalConstant.BACK_OFFICE_USER_TYPE)) {
            return tokenPayload;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
        }
    }

    public TokenPayload validateFrontOfficeToken(String token) {
        TokenPayload tokenPayload = validateToken(token);

        if (tokenPayload.getUserType().equals(GlobalConstant.FRONT_OFFICE_USER_TYPE)) {
            return tokenPayload;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
        }
    }

    private TokenPayload validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims claimsBody = claims.getBody();

            return TokenPayload
                    .builder()
                    .id(claimsBody.get("id", Long.class))
                    .userType(claimsBody.get("userType", Long.class))
                    .email(claimsBody.get("email", String.class))
                    .firstName(claimsBody.get("firstName", String.class))
                    .lastName(claimsBody.get("lastName", String.class))
                    .token(token)
                    .expiredAt(claimsBody.getExpiration())
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
        }
    }
}
