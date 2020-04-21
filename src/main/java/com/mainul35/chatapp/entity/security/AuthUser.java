package com.mainul35.chatapp.entity.security;


import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_auth_details")
public class AuthUser implements UserDetails, Serializable {

	private static final long serialVersionUID = 3581290407206583877L;
	@GeneratedValue
	@Id
    @Column(name = "id")
	private Long id;
    @Column(name = "name", length = 200, nullable = false)
	private String name;
    @Column(name = "phone", length = 30)
    private String phone;
    @Column(name = "email", length = 200, unique = true, nullable = false)
    private String email;
    @Column(name = "username", length = 200, unique = true, updatable = false)
	private String username;
    @Column(name = "password", length = 200, nullable = false)
	private String password;

    @ManyToMany(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinTable(name="user_authority",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
	private Set<Authority> roles;

    @Column(name="created_on")
	private Timestamp dateTime;

    @Column(nullable = false, name = "enabled")
	private boolean enabled;
    @Column(nullable = false, name = "accountNonExpired")
	private boolean accountNonExpired = true;
    @Column(nullable = false, name = "accountNonLocked")
	private boolean accountNonLocked = true;
    @Column(nullable = false, name = "credentialsNonExpired")
	private boolean credentialsNonExpired = true;

    @Override
    public List<Authority> getAuthorities() {
        List<Authority> authorities = new ArrayList<>();
        this.roles.forEach(authority->{
        	roles.add(authority);
        });
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.roles = authorities;
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

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "AuthUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", roles=" + roles +
				", dateTime=" + dateTime +
				", enabled=" + enabled +
				", accountNonExpired=" + accountNonExpired +
				", accountNonLocked=" + accountNonLocked +
				", credentialsNonExpired=" + credentialsNonExpired +
				'}';
	}
}
