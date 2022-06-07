package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.form.TopicForm;
import jp.co.taxis.funsite.service.TopicListService;

@Controller
@RequestMapping("admin")
public class TopicListController {

	@Autowired
	private TopicListService topicListService;

	@RequestMapping(value = "topic/list", method = { RequestMethod.GET })
	public String list(Model model) {
		List<Topic> topicList = topicListService.selectAll();
		if (topicList.isEmpty()) {
		}

		model.addAttribute("topicList", topicList);
		return "admin/topic/list";

	}

	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	public String confirm(@Validated @ModelAttribute("topic") TopicForm topicForm, BindingResult result) {
		if (result.hasErrors()) {
			return "confirm";
		}
		return "confirm";
	}

}
