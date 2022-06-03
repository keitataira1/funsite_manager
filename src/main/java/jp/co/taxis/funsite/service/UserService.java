package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.User;
import jp.co.taxis.funsite.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser(String loginId) {
		User user = userRepository.getUser(loginId);
		return user;
	}

}
