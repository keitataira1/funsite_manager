package jp.co.taxis.funsite.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
public class MemberListServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberListService memberListService;

	@Test
	void testSerach_001_パーセント付与の確認() {

		// モックの引数
		String mockArg1 = "test";
		// モックの戻り値
		List<MemberEntity> memberList = new ArrayList<MemberEntity>();
		// モックの設定
		when(memberRepository.selectLikeName(mockArg1)).thenReturn(memberList);

		// テスト対象の引数
		String targetArg1 = "searchWord";

		// テスト対象メソッド実行
		List<MemberEntity> actualList = memberRepository.selectLikeName(targetArg1);

		// 期待値
		List<MemberEntity> expected = new ArrayList<MemberEntity>();

		// 検証
		assertEquals(expected, actualList);
	}

}

