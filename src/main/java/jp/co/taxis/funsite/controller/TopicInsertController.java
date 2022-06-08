package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.form.TopicForm;
import jp.co.taxis.funsite.service.TopicInsertService;

@Controller
@RequestMapping("admin")
public class TopicInsertController {

	@Autowired
	private TopicInsertService topicInsertService;

	/**
	 * 入力画面表示メソッド.
	 * 
	 * @return input.htmlにリターン
	 */
	@RequestMapping(value = "/topic/insert/input", method = { RequestMethod.GET, RequestMethod.POST })
	public String input(@ModelAttribute("topic") TopicForm topicForm) {

		// 入力画面を出すだけ
		return "admin/topic/insert/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "/topic/insert/confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("topic") @Validated TopicForm topicForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/topic/insert/input";
		}

		// 確認画面の表示だけ
		return "admin/topic/insert/confirm";
	}

	/**
	 * 登録入力画面（DBに送る）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/topic/insert/insert", method = { RequestMethod.POST })
	public String insert(@ModelAttribute("topic") @Validated TopicForm topicForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/topic/insert/input";
		}

		// フォームからエンティティへの変換
		TopicEntity topic = new TopicEntity();
		topic.setId(topicForm.getId());
		topic.setPlayer(topicForm.getPlayer());
		topic.setTopic(topicForm.getTopic());
		topic.setInvalidFlg(topicForm.getInvalidFlg());

		// 登録処理
		topicInsertService.insert(topic);
		topicForm.setId(topic.getId());

		return "admin/topic/insert/complete";
	}

	/**
	 * 登録完了画面.
	 * 
	 * @return View
	 */
	@RequestMapping(value = "/topic/insert/complete", method = { RequestMethod.POST })
	public String complete(@ModelAttribute("topic") @Validated TopicForm topicForm) {

		// 画面を表示するだけ
		return "admin/topic/insert/complete";
	}

}
