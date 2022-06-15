package jp.co.taxis.funsite.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.taxis.funsite.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;

}
