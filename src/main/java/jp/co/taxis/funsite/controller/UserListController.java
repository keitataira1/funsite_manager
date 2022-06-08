package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.UserEntity;
import jp.co.taxis.funsite.service.UserListService;

@Controller
@RequestMapping(value = "admin")
public class UserListController {

	@Autowired
	private UserListService userListService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 一覧画面
	 */

	@RequestMapping(value = "user/list", method = { RequestMethod.GET })
	public String list(Model model) {
		List<UserEntity> userList = userListService.selectAll();
		if (userList.isEmpty()) {
			String message = messageSource.getMessage("list.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}

		model.addAttribute("userList", userList);
		return "admin/user/list";

	}

}