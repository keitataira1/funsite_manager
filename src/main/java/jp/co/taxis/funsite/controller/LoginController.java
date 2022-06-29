package jp.co.taxis.funsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class LoginController {

	@RequestMapping("login")
	public String login(@RequestParam(name="error",defaultValue="false",required = false)String error,Model model) {
		
		if(error.equals("true")) {
			model.addAttribute("message","ログインIDかパスワードが違います。");
		}
		
		return "admin/login";
	}

	@RequestMapping("menu")
	public String menu() {
		return "admin/menu";
	}
}
