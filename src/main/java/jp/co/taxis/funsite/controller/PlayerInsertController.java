
package jp.co.taxis.funsite.controller;

import java.time.LocalDate;
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

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerInsertService;

@Controller
@RequestMapping("admin")
public class PlayerInsertController {

	@Autowired
	private PlayerInsertService playerInsertService;

	@Autowired
	MessageSource messageSource;

	/**
	 * 入力画面表示メソッド.
	 * 
	 * @return input.htmlにリターン
	 */
	@RequestMapping(value = "/player/insert/input", method = { RequestMethod.GET, RequestMethod.POST })
	public String input(@ModelAttribute("player") PlayerForm playerForm) {

		// 入力画面を出すだけ
		return "admin/player/insert/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "/player/insert/confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/player/insert/input";
		}

		// 確認画面の表示だけ
		return "admin/player/insert/confirm";
	}

	/**
	 * 登録入力画面（DBに送る）
	 * 
	 * @return redirect
	 */
	@RequestMapping(value = "/player/insert/complete", method = { RequestMethod.POST })
	public String insert(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/player/insert/input";
		}

		// フォームからエンティティへの変換
		PlayerEntity player = new PlayerEntity();
		player.setId(playerForm.getId());
		player.setName(playerForm.getName());
		player.setBirthday(LocalDate.parse(playerForm.getBirthday()));
		player.setPosition(playerForm.getPosition());
		player.setComment(playerForm.getComment());
		player.setImage(playerForm.getImage());

		// 登録処理

		try {
			// 更新処理
			playerInsertService.insert(player);
			playerForm.setId(player.getId());
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:../list";
		}

		redirectAttrs.addFlashAttribute("player", playerForm);
		return "redirect:complete";

	}

	/**
	 * 登録完了画面
	 */
	@RequestMapping(value = "/player/insert/complete", method = { RequestMethod.GET })
	public String complete() {
		// 画面を表示するだけなので処理はなし。
		return "admin/player/insert/complete";
	}

}
