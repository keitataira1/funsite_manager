package jp.co.taxis.funsite.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.exception.ApplicationException;

@SpringBootTest
class MemberUpdateServiceTest {

	@Autowired
	private MemberUpdateService memberUpdateService;

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM member",
			"INSERT INTO member VALUES (1,'miyama@ryuman.co.jp','admin','宮間','miyama','2001-11-09','1230000','東京都千代田区岩本町2丁目9-3',1,1)",
			"INSERT INTO member VALUES (2,'sugino@ryuman.co.jp','admin','杉野','sugino','2000-01-01','1231111','東京都千代田区岩本町2丁目9-3',1,1)" })
	void testUpdate_001_異常系() {

		// テスト対象の引数
		MemberEntity targetArg1 = new MemberEntity(1, "miyama@ryuman.co.jp", "admin", "宮間", "miyama",
				LocalDate.parse("2001-11-09"), "1230000", "東京都千代田区岩本町2丁目9-3", true, 2);

		try {
			// テスト対象メソッド実行
			memberUpdateService.update(targetArg1);
			fail();
		} catch (ApplicationException e) {
			// 検証
			String actual = e.getMessage();
			// 期待値
			String expected = "optimistic.locking.error";

			assertEquals(expected, actual);
		}
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM member",
			"INSERT INTO member VALUES (1,'miyama@ryuman.co.jp','admin','宮間','miyama','2001-11-09','1230000','東京都千代田区岩本町2丁目9-3',1,1)",
			"INSERT INTO member VALUES (2,'sugino@ryuman.co.jp','admin','杉野','sugino','2000-01-01','1231111','東京都千代田区岩本町2丁目9-3',1,1)" })
	void testUpdate_002_正常系() {

		// テスト対象の引数
		MemberEntity targetArg1 = new MemberEntity(1, "sumino@ryuman.co.jp", "admin", "角野", "sumino",
				LocalDate.parse("1998-05-03"), "1232222", "東京都千代田区岩本町2丁目9-3", true, 1);

		// テスト対象メソッド実行
		try {
			// テスト対象メソッド実行
			memberUpdateService.update(targetArg1);
			
		} catch (ApplicationException e) {
			fail();

		}
	}
	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM member",
			"INSERT INTO member VALUES (1,'miyama@ryuman.co.jp','admin','宮間','miyama','2001-11-09','1230000','東京都千代田区岩本町2丁目9-3',1,1)",
			"INSERT INTO member VALUES (2,'sugino@ryuman.co.jp','admin','杉野','sugino','2000-01-01','1231111','東京都千代田区岩本町2丁目9-3',1,1)" })
	void testUpdate_003_異常系() {

		// テスト対象の引数
		MemberEntity targetArg1 = new MemberEntity(1, "sugino@ryuman.co.jp", "admin", "宮間", "miyama",
				LocalDate.parse("2001-11-09"), "1230000", "東京都千代田区岩本町2丁目9-3", true, 1);

		// テスト対象メソッド実行
		try {
			// テスト対象メソッド実行
			memberUpdateService.update(targetArg1);
			fail();
		} catch (ApplicationException e) {
			// 検証
			String actual = e.getMessage();

			// 期待値
			String expected = "member.samename.error";

			assertEquals(expected, actual);

		}
	}
	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM member",
			"INSERT INTO member VALUES (1,'miyama@ryuman.co.jp','admin','宮間','miyama','2001-11-09','1230000','東京都千代田区岩本町2丁目9-3',1,1)",
			"INSERT INTO member VALUES (2,'sugino@ryuman.co.jp','admin','杉野','sugino','2000-01-01','1231111','東京都千代田区岩本町2丁目9-3',1,1)" })
	void testUpdate_004_異常系() {

		// テスト対象の引数
		MemberEntity targetArg1 = new MemberEntity(1, "miyama@ryuman.co.jp", "admin", "宮間", "sugino",
				LocalDate.parse("2001-11-09"), "1230000", "東京都千代田区岩本町2丁目9-3", true, 1);

		// テスト対象メソッド実行
		try {
			// テスト対象メソッド実行
			memberUpdateService.update(targetArg1);
			fail();
		} catch (ApplicationException e) {
			// 検証
			String actual = e.getMessage();

			// 期待値
			String expected = "member.samename.error";

			assertEquals(expected, actual);

		}
	}

}