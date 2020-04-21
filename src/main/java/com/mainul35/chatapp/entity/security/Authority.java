package com.mainul35.chatapp.entity.security;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ewsd_tbl_authority")
public class Authority implements GrantedAuthority, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2068750034683931763L;
	@GeneratedValue
	@Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "authority", nullable = false, unique = true)
    private String authority;

    public Authority() {
    }

    public Authority(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String role){
        this.authority = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", roleName='" + authority + '\'' +
                '}';
    }
}
