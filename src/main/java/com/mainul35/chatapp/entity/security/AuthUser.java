package com.mainul35.chatapp.entity.security;


import com.mainul35.chatapp.entity.BaseId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity(name = "user_auth_details")
public class AuthUser extends BaseId implements UserDetails, Serializable {

	private static final long serialVersionUID = 3581290407206583877L;

    @Column(name = "name", length = 200, nullable = false)
	private String name;
    @Column(name = "phone", length = 30)
    private String phone;
    @Column(name = "email", length = 200, unique = true, nullable = false)
    private String email;
    @Column(name = "username", length = 200, unique = true, updatable = true)
	private String username;
    @Column(name = "password", length = 200, nullable = false)
	private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Column(nullable = false, name = "enabled")
	private boolean enabled = true;
    @Column(nullable = false, name = "accountNonExpired")
	private boolean accountNonExpired = true;
    @Column(nullable = false, name = "accountNonLocked")
	private boolean accountNonLocked = true;
    @Column(nullable = false, name = "credentialsNonExpired")
	private boolean credentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Role role = this.role;
        var authority = (GrantedAuthority) () -> role.name();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
