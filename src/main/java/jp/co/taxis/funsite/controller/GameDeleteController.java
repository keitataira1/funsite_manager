package jp.co.taxis.funsite.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.form.GameForm;
import jp.co.taxis.funsite.service.GameDeleteService;

@Controller
@RequestMapping("admin")
public class GameDeleteController {

	@Autowired
	private GameDeleteService gameDeleteService;

	/**
	 * 入力画面表示メソッド.
	 */
	@RequestMapping(value = "game/delete", method = { RequestMethod.GET})
	public String confirm(@ModelAttribute("game") GameForm gameForm, Model model) {

		GameEntity game = gameDeleteService.getGame(gameForm.getId());
		gameForm.setId(game.getId());
		gameForm.setMatchDate(game.getMatchDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		gameForm.setMatchTeam(game.getMatchTeam());
		gameForm.setPlace(game.getPlace());

		return "admin/game/delete";
	}

	/**
	 * 削除処理.
	 */
	@RequestMapping(value = "game/delete/delete", method = { RequestMethod.POST })
	public String delete(@ModelAttribute("game") GameForm gameForm, Model model,
			RedirectAttributes redirectAttrs) {

		// 削除処理
		gameDeleteService.delete(gameForm.getId());
		
		redirectAttrs.addFlashAttribute("completeMessage", "削除が完了しました。");
		return "redirect:/admin/game/list";
	}

}
