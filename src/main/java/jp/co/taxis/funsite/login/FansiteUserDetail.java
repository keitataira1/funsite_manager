package jp.co.taxis.funsite.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.taxis.funsite.entity.User;


public class FansiteUserDetail implements UserDetails {

	private final User user;

	public FansiteUserDetail(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		int roleNum = this.user.getRole();

		String roleStr = "USER";
		if (roleNum == 1) {
			roleStr = "ADMIN";
		}
		String role = "ROLE_" + roleStr;

		return AuthorityUtils.createAuthorityList(role);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLoginId();
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
		return true;
	}

}
