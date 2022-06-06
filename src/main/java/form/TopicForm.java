package form;

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
	private String topic;
	private Boolean invalidFlg;
	private Integer version;

}
