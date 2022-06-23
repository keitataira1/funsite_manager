package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
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
	@NotEmpty
	@Pattern(regexp = ".{0,20}",message="メールアドレスは20文字以内です。")
	@Pattern(regexp = "[0-9a-zA-Z@.]+",message="メールアドレスに使用できない文字が含まれています。")
	private String mailAddress;

	//半角大文字などの入力チェック必要
	@NotEmpty
	@Pattern(regexp = ".{0,10}",message="パスワードは10文字以内です。")
	@Pattern(regexp = "[0-9a-zA-Z@#%&]+",message="パスワードに使用できない文字が含まれています。")
	private String password;

	@NotEmpty
	@Pattern(regexp = ".{0,10}")
	private String name;

	@NotEmpty
	@Pattern(regexp = ".{0,10}")
	private String displayName;

	@NotEmpty
	private String birthday;

	//半角数字の入力チェック
	@Pattern(regexp = ".{0,10}",message="郵便番号は10文字以内です。")
	@Pattern(regexp = "[0-9]+",message="郵便番号は半角数字のみです。")
	private String postNumber;

	@Pattern(regexp = ".{0,50}")
	private String address;

	
	private String invalidFlg;


    private Integer version;
}
