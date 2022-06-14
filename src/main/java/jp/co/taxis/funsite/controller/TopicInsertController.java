package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.form.TopicForm;
import jp.co.taxis.funsite.service.PlayerUpdateService;
import jp.co.taxis.funsite.service.TopicInsertService;

@Controller
@RequestMapping("admin")
public class TopicInsertController {

	@Autowired
	private TopicInsertService topicInsertService;

	@Autowired
	private PlayerUpdateService playerUpdateService;

	/**
	 * 入力画面表示メソッド.
	 * 
	 * @return input.htmlにリターン
	 */
	@RequestMapping(value = "topic/insert/input", method = { RequestMethod.GET })
	public String input(@RequestParam("id") Integer playerId, @ModelAttribute("topic") TopicForm topicForm) {

		// 入力画面を出すだけ
		PlayerEntity playerEntity = playerUpdateService.getPlayer(playerId);

		topicForm.setPlayer(playerEntity);
		return "admin/topic/insert/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "topic/insert/confirm", method = { RequestMethod.POST })
	public String confirm(Model model, @ModelAttribute("topic") @Validated TopicForm topicForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/topic/insert/input";
		}

		PlayerEntity playerEntity = playerUpdateService.getPlayer(topicForm.getPlayer().getId());

		model.addAttribute("player", playerEntity);
		// 確認画面の表示だけ
		return "admin/topic/insert/confirm";
	}

	/**
	 * 登録入力画面（DBに送る）
	 * 
	 * @return
	 */
	@RequestMapping(value = "topic/insert/insert", method = { RequestMethod.POST })
	public String insert(@ModelAttribute("topic") @Validated TopicForm topicForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/topic/insert/input";
		}

		// フォームからエンティティへの変換
		TopicEntity topic = new TopicEntity();

		topic.setPlayer(topicForm.getPlayer());
		topic.setTopic(topicForm.getTopic());

		// 登録処理
		topic = topicInsertService.insert(topic);

		redirectAttrs.addFlashAttribute("topic", topic);
		return "redirect:complete";
	}

	/**
	 * 登録完了画面.
	 */
	@RequestMapping(value = "topic/insert/complete", method = { RequestMethod.GET })
	public String complete() {

		// 画面を表示するだけ
		return "admin/topic/insert/complete";
	}

}
