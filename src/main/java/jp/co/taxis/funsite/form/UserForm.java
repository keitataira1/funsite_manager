package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	private Integer id;

	@NotNull
	@Pattern(regexp = ".{1,20}",message="ログインIDは20文字以内です。")
	@Pattern(regexp = "[0-9a-zA-Z@]+",message="メールアドレスに使用できない文字が含まれています。")
	private String login_id;

	@NotNull
	@Pattern(regexp = ".{1,10}",message="パスワードは10文字以内です。")
	@Pattern(regexp = "[0-9a-zA-Z@#%&]+",message="パスワードに使用できない文字が含まれています。")
	private String password;

	@NotNull
	private String role;

	@NotNull
    private Integer version;
}

