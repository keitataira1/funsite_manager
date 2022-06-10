package jp.co.taxis.funsite.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.form.MemberForm;
import jp.co.taxis.funsite.service.MemberUpdateService;

@Controller
@RequestMapping(value = "admin")
public class MemberUpdateController {

	@Autowired
	private MemberUpdateService memberUpdateService;

	@Autowired
	MessageSource messagesource;

	/**
	 * 入力画面表示メソッド.
	 */
	@RequestMapping(value = "/user/input", method = { RequestMethod.GET })
	public String input(@ModelAttribute("member") MemberForm memberForm) {

		MemberEntity member = memberUpdateService.getMember(memberForm.getId());
		memberForm.setInvalidFlg(member.getInvalidFlg());
		memberForm.setMailAddress(member.getMailAddress());
		memberForm.setPassword(member.getPassword());
		memberForm.setName(member.getName());
		memberForm.setDisplayName(member.getDisplayName());
		// playerForm.setBirthday(LocalDate.parse(player.getBirthday()));
		memberForm.setPostNumber(member.getPostNumber());
		memberForm.setAddress(member.getAddress());
		memberForm.setVersion(member.getVersion());
		return "admin/user/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "/user/confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("member") @Validated MemberForm memberForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/user/input";
		}

		// 確認画面の表示だけ
		return "admin/user/confirm";
	}

	/**
	 * 更新処理（DBに送る）
	 * 
	 * @return redirect
	 */
	@RequestMapping(value = "/user/complete", method = { RequestMethod.POST })
	public String update(@ModelAttribute("member") @Validated MemberForm memberForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/user/input";
		}

		// フォームからエンティティへの変換
		MemberEntity member = new MemberEntity();
		member.setId(memberForm.getId());
		member.setMailAddress(memberForm.getMailAddress());
		member.setPassword(memberForm.getPassword());
		member.setName(memberForm.getName());
		member.setDisplayName(memberForm.getDisplayName());
		member.setBirthday(LocalDate.parse(memberForm.getBirthday()));
		member.setPostNumber(memberForm.getPostNumber());
		member.setAddress(memberForm.getAddress());
		member.setInvalidFlg(memberForm.getInvalidFlg());
		member.setVersion(memberForm.getVersion());
		
		
		// 更新処理
		memberUpdateService.update(member);

		return "admin/user/complete";

	}

}
