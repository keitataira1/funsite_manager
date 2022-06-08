package jp.co.taxis.funsite.controller;

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
			playerForm.setName(player.getName());
			//playerForm.setBirthday(LocalDate.parse(player.getBirthday()));
			playerForm.setComment(player.getComment());
			playerForm.setImage(player.getImage());
			playerForm.setVersion(player.getVersion());
			

			return "admin/player/delete/confirm";
		}

		/**
		 * 削除処理.
		 *
		 * @param playerForm          選手
		 * @param model         モデル
		 * @param redirectAttrs リダイレクト属性
		 * @return View
		 */
		@RequestMapping(value = "player/delete/complete", method = { RequestMethod.POST })
		public String delete(@ModelAttribute("player") PlayerForm playerForm, Model model, RedirectAttributes redirectAttrs) {

			// 削除処理
			playerDeleteService.delete(playerForm.getId());
			

			return "admin/player/delete/complete";
		}}

