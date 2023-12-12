package org.java.spring.auth.db.pojo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
	
	public User() { }
	public User(String username, String password, Role... roles) {
		
		setUsername(username);
		setPassword(password);
		setRoles(roles);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public void setRoles(Role... roles) {
		setRoles(List.of(roles));
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
			.toList();
	}
	public boolean isAccountNonExpired() { return true; }
	
	public boolean isAccountNonLocked() { return true; }
	
	public boolean isCredentialsNonExpired() { return true; }
	
	public boolean isEnabled() { return true; }
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getUsername();
	}
	

}
