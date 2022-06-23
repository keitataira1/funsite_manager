package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
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

	private Integer id;

	@NotEmpty
	private String matchDate;

	@NotEmpty
	@Pattern(regexp = ".{0,20}")
	private String matchTeam;

	@NotEmpty
	@Pattern(regexp = ".{0,20}")
	private String place;

}
