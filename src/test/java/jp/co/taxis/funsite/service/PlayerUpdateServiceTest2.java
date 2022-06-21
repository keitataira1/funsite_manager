package jp.co.taxis.funsite.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.PlayerRepository;

@SpringBootTest
public class PlayerUpdateServiceTest2 {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PlayerUpdateService playerUpdateService;

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM player", "INSERT INTO player VALUES (1,'test',null,'test','aaa',null,1)",
			"INSERT INTO player VALUES (2,'test2',null,'test','bbb',null,1)",
			"INSERT INTO player VALUES (3,'test3',null,'test','ccc',null,1)" })
	void testUpdate_002_異常系() {
		// テスト対象の引数
		PlayerEntity targetArg1 = new PlayerEntity(4, "test", null, "test", "aaa", null, 1);

		try {
			// テスト対象メソッド実行
			playerUpdateService.update(targetArg1);
			fail();
		} catch (ApplicationException e) {
			// 検証
			String actual = e.getMessage();

			// 期待値
			String expected = "player.samename.error";
			assertEquals(expected, actual);
		}
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM player", "INSERT INTO player VALUES (1,'test',null,'test','aaa',null,1)",
			"INSERT INTO player VALUES (2,'test2',null,'test','bbb',null,1)",
			"INSERT INTO player VALUES (3,'test3',null,'test','ccc',null,1)" })
	void testUpdate_003_正常系() {
		// テスト対象の引数
		PlayerEntity targetArg1 = new PlayerEntity(1, "test", null, "test", "bbb", null, 1);
		// テスト対象メソッド実行
		try {
			// テスト対象メソッド実行
			playerUpdateService.update(targetArg1);

		} catch (ApplicationException e) {
			fail();
		}

	}
}
