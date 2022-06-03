package jp.co.taxis.funsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class LoginController {

	/**
	 * ログインのハンドラメソッド
	 * @return
	 */
    @RequestMapping("login")
    public String login() {
        return "admin/login";
    }

    @RequestMapping("menu")
    public String menu() {
        return "admin/menu";
    }
}
