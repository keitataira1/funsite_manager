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

import jp.co.taxis.funsite.entity.Player;
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
	 * 入力画面表示メソッド.
	 */
	@RequestMapping(value = "/player/update/input", method = { RequestMethod.GET, RequestMethod.POST })
	public String input(@ModelAttribute("player") PlayerForm playerForm) {

		Player player = playerUpdateService.getPlayer(playerForm.getId());
		player.setName(playerForm.getName());	
		player.setBirthday(LocalDate.parse(playerForm.getBirthday()));
		player.setComment(playerForm.getComment());


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
	@RequestMapping(value = "/player/update/update", method = { RequestMethod.POST })
	public String update(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/player/update/input";
		}

		// フォームからエンティティへの変換
		Player player = new Player();
		player.setId(playerForm.getId());
		player.setName(playerForm.getName());
		player.setBirthday(LocalDate.parse(playerForm.getBirthday()));
		player.setComment(playerForm.getComment());
		
	
			//更新処理
		playerUpdateService.update(player);
			

		return "admin/player/update/complete";
	
	}

	// 画面表示メソッド
	@RequestMapping(value = "/player/update/complete", method = { RequestMethod.POST })
	public String complete() {
		return "admin/player/update/complete";
	}

}
