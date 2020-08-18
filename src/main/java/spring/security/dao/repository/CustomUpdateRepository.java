package spring.security.dao.repository;

public interface CustomUpdateRepository {

    long updateAccountLocked(String nameField, String valueField,
                            String nameFieldForUpdate, boolean accountNonLocked);
}
