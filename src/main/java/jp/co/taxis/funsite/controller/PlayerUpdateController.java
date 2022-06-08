package jp.co.taxis.funsite.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerUpdateService;

@Controller
@RequestMapping(value = "admin")
public class PlayerUpdateController {

	@Autowired
	private PlayerUpdateService playerUpdateService;

	@Autowired
	MessageSource messagesource;

	/**
	 * 入力画面表示メソッド.
	 */
	@RequestMapping(value = "/player/update/input", method = { RequestMethod.GET })
	public String input(@ModelAttribute("player") PlayerForm playerForm) {

		PlayerEntity player = playerUpdateService.getPlayer(playerForm.getId());
		playerForm.setName(player.getName());
		// playerForm.setBirthday(LocalDate.parse(player.getBirthday()));
		playerForm.setComment(player.getComment());
		playerForm.setImage(player.getImage());
		playerForm.setVersion(player.getVersion());

		return "admin/player/update/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "/player/update/confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/player/update/input";
		}

		// 確認画面の表示だけ
		return "admin/player/update/confirm";
	}

	/**
	 * 更新処理（DBに送る）
	 * 
	 * @return redirect
	 */
	@RequestMapping(value = "/player/update/complete", method = { RequestMethod.POST })
	public String update(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/player/update/input";
		}

		// フォームからエンティティへの変換
		PlayerEntity player = new PlayerEntity();
		player.setId(playerForm.getId());
		player.setName(playerForm.getName());
		player.setBirthday(LocalDate.parse(playerForm.getBirthday()));
		player.setComment(playerForm.getComment());
		player.setImage(playerForm.getImage());
		player.setVersion(playerForm.getVersion());

		// 更新処理
		playerUpdateService.update(player);

		return "admin/player/update/complete";

	}

}
