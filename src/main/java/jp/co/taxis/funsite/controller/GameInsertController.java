package jp.co.taxis.funsite.controller;

import java.time.LocalDateTime;
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

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.GameForm;
import jp.co.taxis.funsite.service.GameInsertService;

@Controller
@RequestMapping("admin")
public class GameInsertController {

	@Autowired
	private GameInsertService gameInsertService;

	@Autowired
	MessageSource messageSource;

	/**
	 * 入力画面表示メソッド.
	 * 
	 * @return input.htmlにリターン
	 */
	@RequestMapping(value = "/game/insert/input", method = { RequestMethod.GET, RequestMethod.POST })
	public String input(@ModelAttribute("game") GameForm gameForm) {

		// 入力画面を出すだけ
		return "admin/game/insert/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "/game/insert/confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("game") @Validated GameForm gameForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/game/insert/input";
		}

		// 確認画面の表示だけ
		return "admin/game/insert/confirm";
	}

	/**
	 * 登録入力画面（DBに送る）
	 * 
	 * @return redirect
	 */
	@RequestMapping(value = "/game/insert/insert", method = { RequestMethod.POST })
	public String insert(@ModelAttribute("game") @Validated GameForm gameForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/game/insert/input";
		}

		// フォームからエンティティへの変換
		GameEntity game = new GameEntity();
		game.setId(gameForm.getId());
		game.setMatchDate(LocalDateTime.parse(gameForm.getMatchDate()));
		game.setMatchTeam(gameForm.getMatchTeam());
		game.setPlace(gameForm.getPlace());

		// 登録処理

		try {
			// 更新処理
			gameInsertService.insert(game);
			gameForm.setId(game.getId());
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:../list";
		}

		redirectAttrs.addFlashAttribute("game", gameForm);
		return "redirect:complete";

	}

	/**
	 * 登録完了画面
	 */
	@RequestMapping(value = "/game/insert/complete", method = { RequestMethod.GET })
	public String complete() {
		// 画面を表示するだけなので処理はなし。
		return "admin/game/insert/complete";
	}

}
