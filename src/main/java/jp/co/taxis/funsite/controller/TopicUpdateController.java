package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.form.TopicForm;
import jp.co.taxis.funsite.service.TopicUpdateService;

@Controller
@RequestMapping(value = "admin")
public class TopicUpdateController {

	@Autowired
	private  TopicUpdateService topicUpdateService;
	
		@RequestMapping(value="update/input", method= {RequestMethod.GET, RequestMethod.POST})
		public String input(@ModelAttribute("topic") TopicForm topicForm) {
			
			Topic topic= topicUpdateService.getTopic(topicForm.getId());
			topicForm.setTopic(topic.getTopic());
			
			return "topic/update/input";
			
			
		}
		
}
