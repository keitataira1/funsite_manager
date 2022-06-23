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

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.form.SearchForm;
import jp.co.taxis.funsite.service.MemberListService;

@Controller
@RequestMapping(value = "admin")
public class MemberListController {

	@Autowired
	private MemberListService memberListService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 一覧画面
	 */
	@RequestMapping(value = "/user/list", method = { RequestMethod.GET })
	public String list(@ModelAttribute("search") SearchForm searchForm,Model model) {
		
		

		List<MemberEntity> memberList = memberListService.selectAll();
		if (memberList.isEmpty()) {
			String message = messageSource.getMessage("memberList.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		searchForm.getSearchWord();

		model.addAttribute("memberList", memberList);

		return "/admin/user/list";

	}
	
	

	@RequestMapping(value = "/user/search", method = { RequestMethod.POST })
	public String searchList(@ModelAttribute("search") @Validated SearchForm searchForm, BindingResult result, Model model) {
		
		
		if (result.hasErrors()) {
			return "/admin/user/list";
		}

		List<MemberEntity> searchList = memberListService.selectLikeName(searchForm.getSearchWord());
		if (searchList.isEmpty()) {
			String message = messageSource.getMessage("memberSearch.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		if(searchList.size()>100) {
			searchList = null;
			String message = messageSource.getMessage("memberSearchOver.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}

		model.addAttribute("searchList", searchList);

		return "/admin/user/list";

	}

}