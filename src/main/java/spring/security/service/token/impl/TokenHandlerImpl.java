package spring.security.service.token.impl;


import com.google.common.io.BaseEncoding;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import spring.security.service.token.TokenHandler;
import spring.security.service.yaml.YamlPropertySourceFactory;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.util.Optional;

@PropertySource(value = "classpath:application.yml",
        factory = YamlPropertySourceFactory.class)
@Service
public class TokenHandlerImpl implements TokenHandler {

    private SecretKey secretKey;

    @Value("${key.jwtKey}")
    private String jwtKey;

    public TokenHandlerImpl() {
        //String jwtKey = "jwtkey1234567890";
    }

    @PostConstruct
    private void init () {
        byte[] decodedKey = BaseEncoding.base64().decode(jwtKey);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    @Override
    public Optional<ObjectId> extractUserId(String token) {
        return Optional.empty();
    }

    @Override
    public String generateAccessToken(ObjectId id, LocalDateTime expires) {
        return null;
    }

}
/*    public Optional<ObjectId> extractUserId1(@NonNull String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return Optional
                    .ofNullable(body.getId())
                    .map(ObjectId::new);

        } catch (RuntimeException e) {
            return Optional.empty();
        }

    }

    public String generateAccessToken1(@NonNull ObjectId id, @NonNull LocalDateTime expires) {
        return Jwts.builder()
                .setId(id.toString())
                .setExpiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }*/



