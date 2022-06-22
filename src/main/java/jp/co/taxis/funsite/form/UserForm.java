package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	private Integer id;

	@NotEmpty
	@Pattern(regexp = ".{1,20}")
	private String login_id;

	@NotEmpty
	@Pattern(regexp = ".{1,10}")
	private String password;

	@NotEmpty
	private String role;

	@NotEmpty
    private Integer version;
}

