package jp.co.taxis.funsite.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.TopicForm;
import jp.co.taxis.funsite.service.TopicUpdateService;

@Controller
@RequestMapping(value = "admin")
public class TopicUpdateController {


	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private TopicUpdateService topicUpdateService;

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "topic/update/confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("topic") @Validated TopicForm topicForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/topic/update/input";
		}

		// 確認画面の表示だけ
		return "admin/topic/update/confirm";
	}

	/**
	 * 登録入力画面（DBに送る）
	 * 
	 * @return redirect
	 */
	@RequestMapping(value = "topic/update/update", method = { RequestMethod.POST })
	public String update(@ModelAttribute("topic") @Validated TopicForm topicForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/topic/update/input";
		}

		// フォームからエンティティへの変換
		TopicEntity topic = new TopicEntity();
		topic.setId(topicForm.getId());
		topic.setTopic(topicForm.getTopic());
		
		try {
			//更新処理
			topicUpdateService.update(topic);
		}catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			redirectAttrs.addFlashAttribute("message", message);
			return"redirect:../list";
		}

	redirectAttrs.addFlashAttribute("topic", topicForm);
	return"redirect:admin/topic/update/complete";
	
	}

	// 画面表示メソッド
	@RequestMapping(value = "topic/update/complete", method = { RequestMethod.GET })
	public String complete() {
		return "admin/topic/update/complete";
	}
		
}
