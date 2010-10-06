package com.devnexus.ting.core.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="crypted_password", nullable=false, length=255)
	private String cryptedPassword;

	@Column(name="current_login_at")
	private Timestamp currentLoginAt;

	@Column(name="current_login_ip", length=255)
	private String currentLoginIp;

	@Column(name="last_login_at")
	private Timestamp lastLoginAt;

	@Column(name="last_login_ip", length=255)
	private String lastLoginIp;

	@Column(name="last_request_at")
	private Timestamp lastRequestAt;

	@Column(nullable=false, length=255)
	private String login;

	@Column(name="login_count", nullable=false)
	private Integer loginCount;

	@Column(name="password_salt", nullable=false, length=255)
	private String passwordSalt;

	@Column(name="persistence_token", nullable=false, length=255)
	private String persistenceToken;

	@Column(name="updated_at")
	private Timestamp updatedAt;

    public User() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getCryptedPassword() {
		return this.cryptedPassword;
	}

	public void setCryptedPassword(String cryptedPassword) {
		this.cryptedPassword = cryptedPassword;
	}

	public Timestamp getCurrentLoginAt() {
		return this.currentLoginAt;
	}

	public void setCurrentLoginAt(Timestamp currentLoginAt) {
		this.currentLoginAt = currentLoginAt;
	}

	public String getCurrentLoginIp() {
		return this.currentLoginIp;
	}

	public void setCurrentLoginIp(String currentLoginIp) {
		this.currentLoginIp = currentLoginIp;
	}

	public Timestamp getLastLoginAt() {
		return this.lastLoginAt;
	}

	public void setLastLoginAt(Timestamp lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Timestamp getLastRequestAt() {
		return this.lastRequestAt;
	}

	public void setLastRequestAt(Timestamp lastRequestAt) {
		this.lastRequestAt = lastRequestAt;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPersistenceToken() {
		return this.persistenceToken;
	}

	public void setPersistenceToken(String persistenceToken) {
		this.persistenceToken = persistenceToken;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	//~~~~~Spring Security
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}