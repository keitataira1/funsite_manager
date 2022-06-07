
package jp.co.taxis.funsite.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.Player;
import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerInsertService;

@Controller
@RequestMapping("admin")
public class PlayerInsertController {

	@Autowired
	private PlayerInsertService playerInsertService;

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
	@RequestMapping(value = "/player/insert/insert", method = { RequestMethod.POST })
	public String insert(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/player/insert/input";
		}

		// フォームからエンティティへの変換
		Player player = new Player();
		player.setId(playerForm.getId());
		player.setName(playerForm.getName());
		player.setBirthday(LocalDate.parse(playerForm.getBirthday()));
		player.setComment(playerForm.getComment());

		// 登録処理
		player = playerInsertService.insert(player);
		playerForm.setId(player.getId());

		redirectAttrs.addFlashAttribute("player", playerForm);

		return "redirect:complete";
	}

	/**
	 * 登録完了画面.
	 * 
	 * @return View
	 */
	@RequestMapping(value = "/player/insert/complete", method = { RequestMethod.GET })
	public String complete() {

		// 画面を表示するだけ
		return "admin/player/insert/complete";
	}

}
