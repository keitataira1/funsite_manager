package jp.co.taxis.funsite.service;

	import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.TopicRepository;

	@ExtendWith(SpringExtension.class)
		class TopicUpdateServiceTest {

			@Mock
			private TopicRepository topicRepository;

			@InjectMocks
			private TopicUpdateService topicUpdateService;

			@Test
			void testUpdate_001_異常系() {
				PlayerEntity player = new PlayerEntity();

				// モックの引数
				TopicEntity mockArg1 = new TopicEntity(1, player, "test",true, 1);
				// モックの設定
				when(topicRepository.save(mockArg1))
						.thenThrow(new OptimisticLockingFailureException(null));

				// テスト対象の引数
				TopicEntity targetArg1 = new TopicEntity(1, player, "test", true, 1);

				// 期待値
				String expected = "optimistic.locking.error";

				try {
					// テスト対象メソッド実行
					topicUpdateService.update(targetArg1);
					fail();
				} catch (ApplicationException e) {
					// 検証
					String actual = e.getMessage();
					assertEquals(expected, actual);
				}
			}
}
