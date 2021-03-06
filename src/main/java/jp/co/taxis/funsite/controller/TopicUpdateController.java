package jp.co.taxis.funsite.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.TopicForm;
import jp.co.taxis.funsite.service.PlayerUpdateService;
import jp.co.taxis.funsite.service.TopicUpdateService;

@Controller
@RequestMapping(value = "admin")
public class TopicUpdateController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	private TopicUpdateService topicUpdateService;

	@Autowired
	private PlayerUpdateService playerUpdateService;

	/**
	 * 入力画面表示メソッド.
	 * 
	 * @return input.htmlにリターン
	 */
	@RequestMapping(value = "topic/update/input", method = { RequestMethod.GET })
	public String input(@ModelAttribute("topic") TopicForm topicForm) {

		TopicEntity topic = topicUpdateService.getTopic(topicForm.getId());

		topicForm.setId(topic.getId());
		topicForm.setPlayer(topic.getPlayer());
		topicForm.setTopic(topic.getTopic());
		topicForm.setInvalidFlg(topic.getInvalidFlg().equals(true) ? "invalid" : "valid");
		topicForm.setVersion(topic.getVersion());
		return "admin/topic/update/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "topic/update/confirm", method = { RequestMethod.POST })
	public String confirm(Model model, @ModelAttribute("topic") @Validated TopicForm topicForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/topic/update/input";
		}

		PlayerEntity playerEntity = playerUpdateService.getPlayer(topicForm.getPlayer().getId());

		model.addAttribute("player", playerEntity);
		// 確認画面の表示だけ
		return "admin/topic/update/confirm";
	}

	/**
	 * 更新入力画面（DBに送る）
	 * 
	 * @return redirect
	 */
	@RequestMapping(value = "topic/update/update", method = { RequestMethod.POST })
	public String update(Model model, @ModelAttribute("topic") @Validated TopicForm topicForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/topic/update/input";
		}

		// フォームからエンティティへの変換
		TopicEntity topic = new TopicEntity();

		topic.setId(topicForm.getId());
		topic.setPlayer(topicForm.getPlayer());
		topic.setTopic(topicForm.getTopic());
		topic.setInvalidFlg(topicForm.getInvalidFlg().equals("invalid") ? true : false);
		topic.setVersion(topicForm.getVersion());

		try {
			// 更新処理
			topicUpdateService.update(topic);

		} catch (OptimisticLockingFailureException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:../list";
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			model.addAttribute("message", message);
			return "admin/topic/update/input";

		}

		redirectAttrs.addFlashAttribute("topic", topicForm);
		return "redirect:complete";

	}

	/**
	 * 登録完了画面.
	 */
	@RequestMapping(value = "topic/update/complete", method = { RequestMethod.GET })
	public String complete() {

		// 画面を表示するだけ
		return "admin/topic/update/complete";
	}

}
