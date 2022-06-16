package jp.co.taxis.funsite.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.PlayerRepository;

@ExtendWith(SpringExtension.class)
class PlayerInsertServiceTest {

	@Mock
	private PlayerRepository playerRepository;

	@InjectMocks
	private PlayerInsertService playerInsertService;

	@Test
	void testInsert_001_異常系() {
	
			PlayerEntity mockArg1 = new PlayerEntity(1, "山田太郎", null, "test", "aaa", null, 1);

			// モックの引数
			List<PlayerEntity> test = playerRepository.searchSameName(mockArg1.getName());
			// モックの設定
			when(!test.isEmpty())
			.thenThrow(new ApplicationException("player.samename.error"));

			// テスト対象の引数
			PlayerEntity targetArg1 = new PlayerEntity(2, "山田太郎", null, "test2", "bbb", null, 1);

			// 期待値
			String expected = "player.samename.error";

			try {
				// テスト対象メソッド実行
				playerInsertService.insert(targetArg1);
				fail();
			} catch (ApplicationException e) {
				// 検証
				String actual = e.getMessage();
				assertEquals(expected, actual);
			}

	}}
