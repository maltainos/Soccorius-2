package com.soccoriusapp.mvc.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.soccoriusapp.mvc.entity.RoleEntity;
import com.soccoriusapp.mvc.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoccoriusUserDetails implements UserDetails{

	private UserEntity user;
	private static final long serialVersionUID = 1L;

	public SoccoriusUserDetails(UserEntity user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<RoleEntity> roles = user.getRoles();

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		
		return authorities;
	}
	
	public Integer getId() {
		return user.getId();
	}

	@Override
	public String getPassword() {
		return user.getEncryptPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEmailVerificationStatus();
	}

}
