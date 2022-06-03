package jp.co.taxis.funsite.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.User;
import jp.co.taxis.funsite.service.UserService;

@Service
public class FansiteUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		User user = userService.getUser(loginId);
		if (user == null) {
			throw new UsernameNotFoundException(loginId + " is not found.");
		}
		return new FansiteUserDetail(user);
	}

}
