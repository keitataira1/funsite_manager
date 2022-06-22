package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import jp.co.taxis.funsite.entity.PlayerEntity;
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

	private PlayerEntity player;

	@NotEmpty
	@Pattern(regexp = ".{1,30}")
	private String topic;

	private String invalidFlg;

	private Integer version;

}
