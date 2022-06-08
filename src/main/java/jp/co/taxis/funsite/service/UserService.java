package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.UserEntity;
import jp.co.taxis.funsite.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserEntity getUser(String loginId) {
		UserEntity user = userRepository.getUser(loginId);
		return user;
	}

}
