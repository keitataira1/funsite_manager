package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
	@Pattern(regexp = ".{1,20}")
	private String name;
	
	@Pattern(regexp = ".{0,150}")
	private String itemExplain;

	@NotEmpty
	private Integer price;
	
	private String image;

	private GameEntity game;

}
