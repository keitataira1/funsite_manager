package jp.co.taxis.funsite.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.User;
import jp.co.taxis.funsite.repository.UserRepository;

@Transactional
@Service
public class UserListService {


		@Autowired
		private UserRepository userRepository;

		public List<User> selectAll() {
			List<User> userList = userRepository.findAll();
			return userList;
		}
	}
