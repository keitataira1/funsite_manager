package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.service.TopicListService;

@Controller
@RequestMapping(value = "admin")
public class TopicListController {

	@Autowired
	private TopicListService topicListService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 一覧画面
	 */

	@RequestMapping(value = "topic/list", method = { RequestMethod.GET })
	public String list(Model model) {
		List<TopicEntity> topicList = topicListService.selectAll();
		if (topicList.isEmpty()) {
			String message = messageSource.getMessage("list.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}

		model.addAttribute("topicList", topicList);
		return "admin/topic/list";

	}

}
