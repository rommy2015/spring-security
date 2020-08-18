package spring.security.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import spring.security.dao.domain.User;
import spring.security.dao.domain.UserField;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class UserMongoTemplate {

    private MongoTemplate template;

    @Autowired
    public UserMongoTemplate(MongoTemplate template) {
        this.template = template;
    }


    /**
     * Используем класс Criteria , для создания парвил, по которым
     * автоматически будут сформированы запрсы в базу данных
     */
    public Optional<User> findByUsername(@NonNull String username) {

        return Optional.ofNullable(
                template.findOne(
                        query(where(UserField.USER_NAME.field()).is(username)),
                        User.class));
    }

    public void save(@NonNull User user) {
        template.save(user);
    }


}
