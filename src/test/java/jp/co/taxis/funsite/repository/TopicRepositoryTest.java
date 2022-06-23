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

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.entity.TopicEntity;

@SpringBootTest
public class TopicRepositoryTest {

	@Autowired
	TopicRepository topicRepository;

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM topic" })
	public void testSelectByPlayerId_001_0件() {

		// テスト対象メソッド実行
		List<TopicEntity> actualList = topicRepository.selectByPlayerId(1);

		// 期待値
		List<TopicEntity> expected = new ArrayList<TopicEntity>();

		// 検証
		assertIterableEquals(expected, actualList);

	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM topic",
			"INSERT INTO topic VALUES (1,1,'好きな食べ物',0,1)",
			"INSERT INTO topic VALUES (2,1,'嫌いな食べ物',0,1)",
			"INSERT INTO topic VALUES (3,1,'好きなスポーツ',0,1)" })
	public void testSelectByPlayerId_002_複数件() {

		// テスト対象メソッド実行
		List<TopicEntity> actualList = topicRepository.selectByPlayerId(1);

		// 期待値
		List<TopicEntity> expected = new ArrayList<TopicEntity>();
		PlayerEntity playerEntity = new PlayerEntity();
		playerEntity.setId(1);
		playerEntity.setName("みやま");
		playerEntity.setBirthday(LocalDate.parse("2001-11-09"));
		playerEntity.setPosition("ディフェンス");
		playerEntity.setComment("IDが1です。");
		playerEntity.setImage(null);
		playerEntity.setVersion(1);

		expected.add(new TopicEntity(1, playerEntity, "好きな食べ物", false, 1));
		expected.add(new TopicEntity(2, playerEntity, "嫌いな食べ物", false, 1));
		expected.add(new TopicEntity(3, playerEntity, "好きなスポーツ", false, 1));

		// 検証
		assertIterableEquals(expected, actualList);

	}

	@Test
	@Transactional
	@Rollback
	@Sql(statements = { "DELETE FROM topic", 
			"INSERT INTO topic VALUES (1,1,'好きな食べ物',0,1)",
			"INSERT INTO topic VALUES (2,1,'嫌いな食べ物',0,1)", 
			"INSERT INTO topic VALUES (3,1,'好きなスポーツ',0,1)" })
	public void testSelectByPlayerId_003_0件() {

		// テスト対象メソッド実行
		List<TopicEntity> actualList = topicRepository.selectByPlayerId(5);

		// 期待値
		List<TopicEntity> expected = new ArrayList<TopicEntity>();

		// 検証
		assertIterableEquals(expected, actualList);

	}

}
