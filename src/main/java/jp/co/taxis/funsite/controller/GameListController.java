package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.form.SearchForm;
import jp.co.taxis.funsite.service.GameListService;

@Controller
@RequestMapping(value = "admin")
public class GameListController {

	@Autowired
	private GameListService gameListService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 一覧画面
	 */
	@RequestMapping(value = "game/list", method = { RequestMethod.GET })
	public String list(@ModelAttribute("search") SearchForm searchForm, Model model) {

		List<GameEntity> gameList = gameListService.selectAll();
		if (gameList.isEmpty()) {
			String message = messageSource.getMessage("gameList.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		model.addAttribute("gameList", gameList);
		return "admin/game/list";

	}

	/**
	 * 試合検索メソッド
	 * 
	 */
	@RequestMapping(value = "game/search", method = { RequestMethod.POST })
	public String searchList(@ModelAttribute("search") @Validated SearchForm searchForm, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "admin/game/list";
		}

		List<GameEntity> searchGameList = gameListService.selectLikeMatchTeam(searchForm.getSearchWord());
		if (searchGameList.isEmpty()) {
			String message = messageSource.getMessage("gameSearch.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}

		model.addAttribute("searchGameList", searchGameList);

		return "admin/game/list";

	}

}
