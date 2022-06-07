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

import jp.co.taxis.funsite.entity.Player;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerUpdateService;

@Controller
@RequestMapping(value = "admin")
public class PlayerUpdateController {

	@Autowired
	MessageSource messagesource;
	
	@Autowired
	private PlayerUpdateService playerUpdateService;

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "update/confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/player/update/input";
		}

		// 確認画面の表示だけ
		return "admin/player/update/confirm";
	}

	/**
	 * 登録入力画面（DBに送る）
	 * 
	 * @return redirect
	 */
	@RequestMapping(value = "update/update", method = { RequestMethod.POST })
	public String update(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/player/update/input";
		}

		// フォームからエンティティへの変換
		Player player = new Player();
		player.setId(playerForm.getId());
		player.setName(playerForm.getName());
		player.setBirthday(LocalDate.parse(playerForm.getBirthday()));
		player.setComment(playerForm.getComment());
		
		try {
			//更新処理
			playerUpdateService.update(player);
		}catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messagesource.getMessage(messageKey, null, Locale.getDefault());
			redirectAttrs.addFlashAttribute("message", message);
			return"redirect:../list";
		}

	redirectAttrs.addFlashAttribute("player", playerForm);
	return"redirect:complete";
	
	}

	// 画面表示メソッド
	@RequestMapping(value = "update/complete", method = { RequestMethod.GET })
	public String complete() {
		return "admin/player/update/complete";
	}

}
