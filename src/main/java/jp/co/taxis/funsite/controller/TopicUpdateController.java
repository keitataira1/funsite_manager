package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.form.TopicForm;
import jp.co.taxis.funsite.service.TopicListService;

@Controller
@RequestMapping("admin")
public class TopicUpdateController {
	
	@Autowired
	private TopicListService topicListService;
	
	@RequestMapping(value="update/input", method= {RequestMethod.GET, RequestMethod.POST})
	public String input(@ModelAttribute("topic") TopicForm tpoicForm) {
		
		Topic topic = topicListService.getTopic(tpoicForm.getId());
		tpoicForm.setPlayer(topic.getPlayer());
		tpoicForm.setTopic(topic.getTopic());
		
		return"admin/topic/update";
	}

}
