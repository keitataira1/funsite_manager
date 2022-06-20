package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.form.SearchForm;
import jp.co.taxis.funsite.service.ItemListService;

@Controller
@RequestMapping(value = "admin")
public class ItemListController {

	@Autowired
	private ItemListService itemListService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 一覧画面
	 */

	@RequestMapping(value = "/item/list", method = { RequestMethod.GET })
	public String list(@ModelAttribute("itemSearch") SearchForm searchForm,Model model) {

		List<ItemEntity> itemList = itemListService.selectAll();
		if (itemList.isEmpty()) {
			String message = messageSource.getMessage("itemList.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		model.addAttribute("itemList", itemList);
		return "admin/item/list";

	}
	

	@RequestMapping(value = "/item/search", method = { RequestMethod.POST })
	public String searchList(@ModelAttribute("itemSearch") SearchForm searchForm, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "/admin/item/list";
		}

		List<ItemEntity> itemSearchList = itemListService.selectLikeItem(searchForm.getSearchItem());
		if (itemSearchList.isEmpty()) {
			String message = messageSource.getMessage("itemSearch.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}

		model.addAttribute("itemSearchList", itemSearchList);

		return "/admin/item/list";

	}
}
