package spring.security.service.dto.user;

import org.bson.types.ObjectId;
import spring.security.dao.domain.Role;

import java.util.List;
import java.util.Objects;

public class UserDto {

    private ObjectId id;

    private String username;

    private List<Role> authorities;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    public UserDto() {
    }

    private UserDto(Builder builder) {
        setId(builder.id);
        setUsername(builder.username);
        setAuthorities(builder.authorities);
        setPassword(builder.password);
        setAccountNonExpired(builder.accountNonExpired);
        setAccountNonLocked(builder.accountNonLocked);
        setCredentialsNonExpired(builder.credentialsNonExpired);
        setEnabled(builder.enabled);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return accountNonExpired == userDto.accountNonExpired &&
                accountNonLocked == userDto.accountNonLocked &&
                credentialsNonExpired == userDto.credentialsNonExpired &&
                enabled == userDto.enabled &&
                Objects.equals(id, userDto.id) &&
                Objects.equals(username, userDto.username) &&
                Objects.equals(authorities, userDto.authorities) &&
                Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, authorities, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authorities=" + authorities +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }


    public static final class Builder {
        private ObjectId id;
        private String username;
        private List<Role> authorities;
        private String password;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;

        private Builder() {
        }

        public Builder id(ObjectId val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder authorities(List<Role> val) {
            authorities = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder accountNonExpired(boolean val) {
            accountNonExpired = val;
            return this;
        }

        public Builder accountNonLocked(boolean val) {
            accountNonLocked = val;
            return this;
        }

        public Builder credentialsNonExpired(boolean val) {
            credentialsNonExpired = val;
            return this;
        }

        public Builder enabled(boolean val) {
            enabled = val;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
