package jp.co.taxis.funsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.form.TopicForm;

@Controller
@RequestMapping("topic")
public class TopicController {

	/**
	 * トピックのハンドラメソッド
	 * 
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String input(@ModelAttribute("topic") TopicForm topicForm) {
		return "input";
	}

	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	public String confirm(@Validated @ModelAttribute("topic") TopicForm topicForm, BindingResult result) {
		if (result.hasErrors()) {
			return "confirm";
		}
		return "confirm";
	}

}
