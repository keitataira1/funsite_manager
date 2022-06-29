package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	private Integer id;

	@NotEmpty
	private String loginId;

	@NotEmpty
	private String password;

	@NotNull
	private String role;

	@NotNull
    private Integer version;
}

