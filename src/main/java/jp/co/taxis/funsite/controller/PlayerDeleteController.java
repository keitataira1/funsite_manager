package jp.co.taxis.funsite.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.Player;
import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerDeleteService;

@Controller
@RequestMapping("admin")
public class PlayerDeleteController {
	
	
	public class ItemDeleteController {

		@Autowired
		private PlayerDeleteService playerDeleteService;

		/**
		 * 削除確認画面.
		 *
		 * @param playerForm  選手
		 * @param model モデル
		 * @return View
		 */
		@RequestMapping(value = "player/delete/confirm", method = { RequestMethod.GET, RequestMethod.POST })
		public String confirm(@ModelAttribute("player") PlayerForm playerForm, Model model) {

			Player player = playerDeleteService.getPlayer(playerForm.getId());
			player.setId(playerForm.getId());
			player.setName(playerForm.getName());
			player.setBirthday(LocalDate.parse(playerForm.getBirthday()));
			player.setComment(playerForm.getComment());
			

			return "player/delete/confirm";
		}

		@RequestMapping(value = "delete/cancel", method = { RequestMethod.POST })
		public String confirmCancel() {
			return "redirect:../list";
		}

		/**
		 * 削除処理.
		 *
		 * @param playerForm          選手
		 * @param model         モデル
		 * @param redirectAttrs リダイレクト属性
		 * @return View
		 */
		@RequestMapping(value = "delete/delete", method = { RequestMethod.POST })
		public String delete(@ModelAttribute("player") PlayerForm playerForm, Model model, RedirectAttributes redirectAttrs) {

			// 削除処理
			playerDeleteService.delete(playerForm.getId());
			redirectAttrs.addFlashAttribute("id", playerForm.getId());

			return "redirect:complete";
		}

		/**
		 * 削除完了画面.
		 *
		 * @return View
		 */
		@RequestMapping(value = "delete/complete", method = { RequestMethod.GET })
		public String complete() {
			return "player/delete/complete";
		}
	}
}
