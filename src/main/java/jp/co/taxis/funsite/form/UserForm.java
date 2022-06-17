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
	@Pattern(regexp = ".{0,20}")
	private String login_id;

	@NotNull
	@Pattern(regexp = ".{0,10}")
	private String password;

	@NotNull
	private String role;

	@NotNull
    private Integer version;
}

