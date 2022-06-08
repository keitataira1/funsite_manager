package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jp.co.taxis.funsite.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * topic用Form.
 * 
 * @author topic
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicForm {

	private Integer id;

	private Player player;

	@Pattern(regexp = ".{1,30}")
	@NotNull
	private String topic;

	private Boolean invalidFlg;

	private Integer version;

}
