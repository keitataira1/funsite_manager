package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//手直し・追加必要？
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerForm {

	private Integer id;

	@NotNull
	@Pattern(regexp = ".{1,10}")
	private String name;

	private String birthday;

	@Pattern(regexp = ".{1,10}")
	private String position;

	@Pattern(regexp = ".{0,100}")
	private String comment;

	private String image;

	private Integer version;

}