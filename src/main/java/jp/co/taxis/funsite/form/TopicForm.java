package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

	@Pattern(regexp = ".{1,30}")
	@NotNull
	private String topic;

	private Integer version;

}
