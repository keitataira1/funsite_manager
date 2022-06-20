package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * game用Form.
 * 
 * @author game
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameForm {

	@NotNull
	private Integer id;

	@NotNull
	private String matchDate;

	@NotNull
	@Pattern(regexp = ".{1,20}")
	private String matchTeam;

	@NotNull
	@Pattern(regexp = ".{1,20}")
	private String place;

}
