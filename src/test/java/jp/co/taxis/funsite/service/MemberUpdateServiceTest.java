package jp.co.taxis.funsite.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
public class MemberUpdateServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberUpdateService memberUpdateService;

	@Test
	void testUpdate_001_正常系() {
		
		// モックの引数
		MemberEntity mockArg1 = new MemberEntity();
		// モックの戻り値
		MemberEntity member = new MemberEntity();
		// モックの設定
		when(memberRepository.save(mockArg1)).thenReturn(member);

		// テスト対象の引数
		MemberEntity targetArg1 = new MemberEntity();

		// 期待値
		MemberEntity expected = new MemberEntity();

		// テスト対象メソッド実行
		MemberEntity actual = memberRepository.save(targetArg1);

		// 検証
		assertEquals(expected, actual);
	}
}
