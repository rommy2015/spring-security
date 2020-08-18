package spring.security.dao.repository.impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import spring.security.dao.domain.User;
import spring.security.dao.repository.CustomUpdateRepository;

@Repository
public class CustomUpdateRepositoryImpl implements CustomUpdateRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public CustomUpdateRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public long updateAccountLocked(String nameField, String valueField, String nameFieldForUpdate, boolean accountNonLocked) {

        Query query = new Query(Criteria.where(nameField).is(valueField));

        Update update = new Update();
        update.set(nameFieldForUpdate, accountNonLocked);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);

        /*получаем количество документов, которые были модифицированы, согласно данному
        * запросу*/
        long modifiedCount = updateResult.getModifiedCount();

        if (modifiedCount == 0) return 0L;

        return modifiedCount;
    }
}
