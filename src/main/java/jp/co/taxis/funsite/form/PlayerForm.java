package jp.co.taxis.funsite.form;

import java.time.LocalDate;

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

	@Pattern(regexp = ".{1,10}")
	private String name;

	@NotNull
	private LocalDate birthday;

	@Pattern(regexp = ".{1,10}")
	private String position;

	@Pattern(regexp = ".{0,100}")
	private String comment;

	private String image;

	private Integer version;

}