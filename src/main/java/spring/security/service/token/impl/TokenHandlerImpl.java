package spring.security.service.token.impl;


import com.google.common.io.BaseEncoding;
import io.jsonwebtoken.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import spring.security.service.token.TokenHandler;
import spring.security.service.yaml.YamlPropertySourceFactory;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@PropertySource(value = "classpath:application.yml",
        factory = YamlPropertySourceFactory.class)
@Service
public class TokenHandlerImpl implements TokenHandler {

    private SecretKey secretKey;

    @Value("${key.jwtKey}")
    private String jwtKey;

    public TokenHandlerImpl() {
    }

    @PostConstruct
    private void init () {
        byte[] decodedKey = BaseEncoding.base64().decode(jwtKey);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    @Override
    public Optional<ObjectId> extractUserId(String token) {

        try {

            JwtParser parser = Jwts.parser();
            JwtParser parserSigningKey = parser.setSigningKey(secretKey);

            Jws<Claims> claimsJws = parserSigningKey.parseClaimsJws(token);

            Claims body = claimsJws.getBody();

            return Optional
                    .ofNullable(body.getId())
                    .map(ObjectId::new);

        } catch (RuntimeException e) {

            return Optional.empty();
        }
    }

    @Override
    public String generateAccessToken(ObjectId id, LocalDateTime expires) {

        ZoneId systemDefault = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = expires.atZone(systemDefault);
        Instant instant = zonedDateTime.toInstant();

        Date fromLocalDateTime = Date.from(instant);

        return Jwts.builder()
                .setId(id.toString())
                .setExpiration(fromLocalDateTime)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

}
