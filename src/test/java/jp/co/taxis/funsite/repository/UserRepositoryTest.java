package jp.co.taxis.funsite.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import jp.co.taxis.funsite.entity.UserEntity;
import jp.co.taxis.funsite.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { 
			"DELETE FROM operation_user" ,
			"INSERT INTO operation_user VALUES (null, 'admin', 'admin', 1, 1)",
			"INSERT INTO operation_user VALUES (null, 'test', 'test', 1, 1)"
	})
	public void testgetUser_001_0件() {

		// テスト対象メソッド実行
		UserEntity actual = userRepository.getUser("sugino");

		// 検証
		assertNull(actual);
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { 
			"DELETE FROM operation_user" ,
			"INSERT INTO operation_user VALUES (1, 'admin', 'admin', 1, 1)",
			"INSERT INTO operation_user VALUES (null, 'test', 'test', 1, 1)"
	})
	public void testgetUser_002_1件() {

		// テスト対象メソッド実行
		UserEntity actual = userRepository.getUser("admin");

		// 期待値
		UserEntity expected = new UserEntity(1, "admin", "admin", 1, 1);

		// 検証
		assertEquals(expected, actual);
	}

}
