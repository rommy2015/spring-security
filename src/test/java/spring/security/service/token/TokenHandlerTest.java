package spring.security.service.token;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TokenHandlerTest {

    @Autowired
    private TokenHandler tokenHandler;

    @Test
    void generateAccessToken() {

        ObjectId objectId = new ObjectId("589742e3e4284e3c76418e2d");

        String token = tokenHandler.generateAccessToken(objectId, LocalDateTime.now().plusDays(14));

        System.out.println();

        System.out.println("token : " + token);

        System.out.println();

        Optional<ObjectId> id = tokenHandler.extractUserId(token);

        System.out.println();

        System.out.println(id.get().toString());

        System.out.println();
    }
}
