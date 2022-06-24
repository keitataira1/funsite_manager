package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.taxis.funsite.form.UserForm;

@Controller
@RequestMapping("admin")
public class LoginController {
	
	@Autowired
	MessageSource messageSource;

	/**
	 * ログインのハンドラメソッド
	 * @return
	 */
    @RequestMapping("login")
    public String login(@Validated UserForm userForm, BindingResult result) {
    	
    	
    	
        return "admin/login";
    }

    @RequestMapping("menu")
    public String menu() {
        return "admin/menu";
    }
}
