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
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.PlayerRepository;
import jp.co.taxis.funsite.service.PlayerUpdateService;

@ExtendWith(SpringExtension.class)
	class PlayerUpdateServiceTest {

		@Mock
		private PlayerRepository playerRepository;

		@InjectMocks
		private PlayerUpdateService playerUpdateService;

		@Test
		void testUpdate_001_異常系() {

			// モックの引数
			PlayerEntity mockArg1 = new PlayerEntity(1, "test", null, null, "aaa", null, 1);
			// モックの設定
			when(playerRepository.save(mockArg1))
					.thenThrow(new OptimisticLockingFailureException(null));

			// テスト対象の引数
			PlayerEntity targetArg1 = new PlayerEntity(1, "test", null, null, "aaa", null, 1);

			// 期待値
			String expected = "optimistic.locking.error";

			try {
				// テスト対象メソッド実行
				playerUpdateService.update(targetArg1);
				fail();
			} catch (ApplicationException e) {
				// 検証
				String actual = e.getMessage();
				assertEquals(expected, actual);
			}
		}

	}
