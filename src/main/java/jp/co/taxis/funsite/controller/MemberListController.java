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
	
	//平良さんコメント
	//selectLikeName()の()内はgetで取得する
	//新しく検索用のFormクラスを作る
	//memberList以外のmodeladdAttribute?を作ってThymeleafに渡す
	//htmlの検索フォームにはth:objectを利用

	@RequestMapping(value = "user/list", method = { RequestMethod.GET })
	public String list(@ModelAttribute("search") SearchForm searchForm, BindingResult result, Model model) {
		List<MemberEntity> memberList = memberListService.selectAll();
		if (memberList.isEmpty()) {
			String message = messageSource.getMessage("list.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		
		//List<MemberEntity> memberSearchList = memberListService.selectLikeName(searchForm.getName());
		
		model.addAttribute("memberList", memberList);
		
		//model.addAttribute("memberSearchList", memberSearchList);
		
		return "admin/user/list";
		
		

	}

}