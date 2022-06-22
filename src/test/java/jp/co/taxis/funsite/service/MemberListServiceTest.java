package jp.co.taxis.funsite.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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
	void testSerach_001_正常系() {

		// モックの引数
		String mockArg1 = "%test%";
		// モックの戻り値
		List<MemberEntity> memberList = new ArrayList<MemberEntity>();
		memberList.add(new MemberEntity(1, "miyama@ryuman.co.jp", "admin", "宮間", "miyama", LocalDate.parse("2001-11-09"),
				"1230000", "東京都千代田区岩本町2丁目9-3", true, 1));
		memberList.add(new MemberEntity(2, "sugino@ryuman.co.jp", "admin", "杉野", "sugino", LocalDate.parse("2000-01-01"),
				"1231111", "東京都千代田区岩本町2丁目9-3", true, 1));
		memberList.add(new MemberEntity(3, "sumino@ryuman.co.jp", "admin", "角野", "sumino", LocalDate.parse("1998-05-03"),
				"1232222", "東京都千代田区岩本町2丁目9-3", true, 1));
		// モックの設定
		when(memberRepository.selectLikeName(mockArg1)).thenReturn(memberList);

		// テスト対象の引数
		String targetArg1 = "test";

		// テスト対象メソッド実行
		List<MemberEntity> actualList = memberRepository.selectLikeName(targetArg1);

		// 期待値
		List<MemberEntity> expected = new ArrayList<MemberEntity>();
		expected.add(new MemberEntity(1, "miyama@ryuman.co.jp", "admin", "宮間", "miyama", LocalDate.parse("2001-11-09"),
				"1230000", "東京都千代田区岩本町2丁目9-3", true, 1));
		expected.add(new MemberEntity(2, "sugino@ryuman.co.jp", "admin", "杉野", "sugino", LocalDate.parse("2000-01-01"),
				"1231111", "東京都千代田区岩本町2丁目9-3", true, 1));
		expected.add(new MemberEntity(3, "sumino@ryuman.co.jp", "admin", "角野", "sumino", LocalDate.parse("1998-05-03"),
				"1232222", "東京都千代田区岩本町2丁目9-3", true, 1));

		// 検証
		assertEquals(expected, actualList);
	}

}

