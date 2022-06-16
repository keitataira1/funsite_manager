package jp.co.taxis.funsite.Repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.repository.PlayerRepository;

@SpringBootTest
public class PlayerRepositoryTest {
	@Autowired
	PlayerRepository playerRepository;

	@Test
	@Transactional
	@Rollback
	@Sql(statements = {
			"DELETE FROM player",
			"INSERT INTO player VALUES (1,'山田太郎',null,'フォワード','aaa',null,1)",
			"INSERT INTO player VALUES (2,'井上太郎',null,'ミッドフィルダー','bbb',null,1)",
			"INSERT INTO player VALUES (3,'斎藤たかし',null,'ディフェンダー','ccc',	null,1)"
	})
	public void testSearch_001_0件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.searchSameName("山田");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();

		// 検証
		assertIterableEquals(expected, actualList);;
	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = {
			"DELETE FROM player",
			"INSERT INTO player VALUES (1,'山田太郎',null,'フォワード','aaa',null,1)",
			"INSERT INTO player VALUES (2,'井上太郎',null,'ミッドフィルダー','bbb',null,1)",
			"INSERT INTO player VALUES (3,'斎藤たかし',null,'ディフェンダー','ccc',	null,1)"
	})
	public void testSearch_002_1件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.searchSameName("山田太郎");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();
		expected.add(new PlayerEntity(1,"山田太郎",null,"フォワード","aaa",null,1));

		// 検証
		assertIterableEquals(expected, actualList);
	}

	
	
	@Test
	@Transactional
	@Rollback
	@Sql(statements = {
			"DELETE FROM player",
			"INSERT INTO player VALUES (1,'山田太郎',null,'フォワード','aaa',null,1)",
			"INSERT INTO player VALUES (2,'井上太郎',null,'ミッドフィルダー','bbb',null,1)",
			"INSERT INTO player VALUES (3,'山田太郎',null,'ディフェンダー','ccc',null,1)"
	})
	public void testSearch_003_複数件() {

		// テスト対象メソッド実行
		List<PlayerEntity> actualList = playerRepository.searchSameName("山田太郎");

		// 期待値
		List<PlayerEntity> expected = new ArrayList<PlayerEntity>();
		expected.add(new PlayerEntity(1,"山田太郎",null,"フォワード","aaa",null,1));
		expected.add(new PlayerEntity (3,"山田太郎",null,"ディフェンダー","ccc",null,1));

		// 検証
		assertIterableEquals(expected, actualList);
	}


@Test
@Transactional
@Rollback
@Sql(statements = {
		"DELETE FROM player",
		"INSERT INTO player VALUES (1,'山田太郎',null,'フォワード','aaa',null,1)",
		"INSERT INTO player VALUES (2,'井上太郎',null,'ミッドフィルダー','bbb',null,1)",
		"INSERT INTO player VALUES (3,'斎藤たかし',null,'ディフェンダー','ccc',	null,1)"
})
public void testSearch_004_空文字() {

	// テスト対象メソッド実行
	List<PlayerEntity> actualList = playerRepository.searchSameName("");

	// 期待値
	List<PlayerEntity> expected = new ArrayList<PlayerEntity>();

	assertIterableEquals(expected, actualList);
}
}
