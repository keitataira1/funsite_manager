package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import jp.co.taxis.funsite.entity.GameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {
	private Integer id;

	@NotEmpty
	@Pattern(regexp = ".{0,20}")
	private String name;
	
	@Pattern(regexp = ".{0,150}")
	private String itemExplain;

	@NotNull
	private Integer price;
	
	private MultipartFile image;

	private GameEntity gameId;

	private String imageFileName;

	private Integer version;

}
