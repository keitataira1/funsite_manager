
package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.Player;
import jp.co.taxis.funsite.service.PlayerListService;

@Controller
@RequestMapping(value = "admin")
public class PlayerListController {

	@Autowired
	private PlayerListService playerListService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 一覧画面
	 */

	@RequestMapping(value = "player/list", method = { RequestMethod.GET })
	public String list(Model model) {
		List<Player> playerList = playerListService.selectAll();
		if (playerList.isEmpty()) {
			String message = messageSource.getMessage("list.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}

		model.addAttribute("playerList", playerList);
		return "admin/player/list";

	}

}