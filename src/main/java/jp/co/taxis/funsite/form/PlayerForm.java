package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerForm {

	private Integer id;

	@NotEmpty
	@Pattern(regexp = ".{0,10}")
	private String name;

	@NotEmpty
	private String birthday;

	@Pattern(regexp = ".{0,10}")
	private String position;

	@Pattern(regexp = ".{0,100}")
	private String comment;

	private MultipartFile image;

	private String imageFileName;

	private Integer version;

}