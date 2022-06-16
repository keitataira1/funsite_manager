package jp.co.taxis.funsite.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.MemberEntity;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM member" })
	public void testSelectLikeName_001_0件() {

		// テスト対象メソッド実行
		List<MemberEntity> actualList = memberRepository.selectLikeName("%野%");

		// 期待値
		List<MemberEntity> expected = new ArrayList<MemberEntity>();

		// 検証
		assertIterableEquals(expected, actualList);

	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM member",
			"INSERT INTO member VALUES (1,'miyama@ryuman.co.jp','admin','宮間','miyama','2001-11-09','1230000','東京都千代田区岩本町2丁目9-3',1,1)",
			"INSERT INTO member VALUES (2,'sugino@ryuman.co.jp','admin','杉野','sugino','2000-01-01','1231111','東京都千代田区岩本町2丁目9-3',1,1)",
			"INSERT INTO member VALUES (3,'sumino@ryuman.co.jp','admin','角野','sumino','1998-05-03','1232222','東京都千代田区岩本町2丁目9-3',1,1)" })
	public void testSelectLikeName_002_複数件() {

		// テスト対象メソッド実行
		List<MemberEntity> actualList = memberRepository.selectLikeName("%野%");

		// 期待値
		List<MemberEntity> expected = new ArrayList<MemberEntity>();
		expected.add(new MemberEntity(2, "sugino@ryuman.co.jp", "admin", "杉野", "sugino", LocalDate.parse("2000-01-01"),
				"1231111", "東京都千代田区岩本町2丁目9-3", true, 1));
		expected.add(new MemberEntity(3, "sumino@ryuman.co.jp", "admin", "角野", "sumino", LocalDate.parse("1998-05-03"),
				"1232222", "東京都千代田区岩本町2丁目9-3", true, 1));

		// 検証
		assertIterableEquals(expected, actualList);

	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM member",
			"INSERT INTO member VALUES (1,'miyama@ryuman.co.jp','admin','宮間','miyama','2001-11-09','1230000','東京都千代田区岩本町2丁目9-3',1,1)",
			"INSERT INTO member VALUES (2,'sugino@ryuman.co.jp','admin','杉野','sugino','2000-01-01','1231111','東京都千代田区岩本町2丁目9-3',1,1)",
			"INSERT INTO member VALUES (3,'sumino@ryuman.co.jp','admin','角野','sumino','1998-05-03','1232222','東京都千代田区岩本町2丁目9-3',1,1)" })
	public void testSelectLikeName_003_0件() {

		// テスト対象メソッド実行
		List<MemberEntity> actualList = memberRepository.selectLikeName("%鬱%");

		// 期待値
		List<MemberEntity> expected = new ArrayList<MemberEntity>();

		// 検証
		assertIterableEquals(expected, actualList);

	}

}
