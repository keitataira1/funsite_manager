package jp.co.taxis.funsite.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.MemberForm;
import jp.co.taxis.funsite.service.MemberUpdateService;

@Controller
@RequestMapping(value = "admin")
public class MemberUpdateController {

	@Autowired
	private MemberUpdateService memberUpdateService;

	@Autowired
	MessageSource messageSource;

	/**
	 * 入力画面表示メソッド.
	 */
	@RequestMapping(value = "/user/input", method = { RequestMethod.GET })
	public String input(@ModelAttribute("member") MemberForm memberForm) {

		MemberEntity member = memberUpdateService.getMember(memberForm.getId());
		memberForm.setInvalidFlg(member.getInvalidFlg().equals(true) ? "invalid" : "valid");
		memberForm.setMailAddress(member.getMailAddress());
		memberForm.setPassword(member.getPassword());
		memberForm.setName(member.getName());
		memberForm.setDisplayName(member.getDisplayName());
		memberForm.setBirthday(member.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		memberForm.setPostNumber(member.getPostNumber());
		memberForm.setAddress(member.getAddress());
		memberForm.setVersion(member.getVersion());
		return "admin/user/input";
	}


	/**
	 * 更新処理（DBに送る）
	 */
	@RequestMapping(value = "/user/update", method = { RequestMethod.POST })
	public String update(@ModelAttribute("member") @Validated MemberForm memberForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

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
		member.setInvalidFlg(memberForm.getInvalidFlg().equals("invalid") ? true : false);
		member.setVersion(memberForm.getVersion());

		try {
			// 更新処理
			memberUpdateService.update(member);
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:../list";
		}
		redirectAttrs.addFlashAttribute("completeMessage", "更新が完了しました。");
		return "redirect:/admin/user/list";

	}

}