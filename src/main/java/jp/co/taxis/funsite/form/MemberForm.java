package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {
	
	private Integer id;

	//半角大文字などの入力チェック必要
	@NotNull
	@Pattern(regexp = ".{1,20}")
	private String mailAddress;

	//半角大文字などの入力チェック必要
	@NotNull
	@Pattern(regexp = ".{1,10}")
	private String password;

	@NotNull
	@Pattern(regexp = ".{1,10}")
	private String name;

	@NotNull
	@Pattern(regexp = ".{1,10}")
	private String displayName;

	
	private String birthday;

	//半角数字の入力チェック
	@Pattern(regexp = ".{1,10}")
	private String postNumber;

	@Pattern(regexp = ".{0,50}")
	private String address;

	
	private Boolean invalidFlg;


    private int version;
}
