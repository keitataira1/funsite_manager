package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotNull;
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

	@NotNull
	@Pattern(regexp = ".{1,20}")
	private String name;

	@NotNull
	private Integer price;

	private GameEntity game;

}
