package spring.security.service.token;

import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenHandler {

    /**
     * извлекаем иденитификатор пользователя
     * @param token
     * @return
     */
    Optional<ObjectId> extractUserId(@NonNull String token);


    /**
     * генерируем маркер безопасности
     * @param id идентификатор пользователя, который прошел аутентификацию и авторизацию
     * @param expires - время действия маркера безопасности.
     * @return
     */
    String generateAccessToken(@NonNull ObjectId id, @NonNull LocalDateTime expires);
}
