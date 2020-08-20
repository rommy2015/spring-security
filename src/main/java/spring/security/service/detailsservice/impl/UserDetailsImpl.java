package spring.security.service.detailsservice.impl;

import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.security.dao.domain.Role;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

    private ObjectId id;

    private String username;

    private List<Role> authorities;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    public UserDetailsImpl() {
    }

    public ObjectId getId() {
        return id;
    }

    private UserDetailsImpl(Builder builder) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

        public UserDetailsImpl build() {
            return new UserDetailsImpl(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl that = (UserDetailsImpl) o;
        return accountNonExpired == that.accountNonExpired &&
                accountNonLocked == that.accountNonLocked &&
                credentialsNonExpired == that.credentialsNonExpired &&
                enabled == that.enabled &&
                Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(authorities, that.authorities) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, authorities, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }

    @Override
    public String toString() {
        return "UserDetailsImpl{" +
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
}
