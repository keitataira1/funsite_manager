package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.MemberEntity;
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

	@RequestMapping(value = "user/list", method = { RequestMethod.GET })
	public String list(Model model) {
		List<MemberEntity> memberList = memberListService.selectAll();
		if (memberList.isEmpty()) {
			String message = messageSource.getMessage("list.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		
		List<MemberEntity> memberList2 = memberListService.selectLikeName();
		
		
		
		model.addAttribute("memberList", memberList);

		
		return "admin/user/list";

	}

}