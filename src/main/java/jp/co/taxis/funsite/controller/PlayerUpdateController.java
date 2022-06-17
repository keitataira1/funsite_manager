package jp.co.taxis.funsite.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerUpdateService;

@Controller
@RequestMapping(value = "admin")
public class PlayerUpdateController {

	@Autowired
	private PlayerUpdateService playerUpdateService;

	@Autowired
	MessageSource messageSource;

	/**
	 * 入力画面表示メソッド.
	 */
	@GetMapping("/player/update/input")
	public String input(@ModelAttribute("player") PlayerForm playerForm) {

		PlayerEntity player = playerUpdateService.getPlayer(playerForm.getId());
		// entityからformに変換
		playerForm.setName(player.getName());
		playerForm.setBirthday(player.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		playerForm.setPosition(player.getPosition());
		playerForm.setComment(player.getComment());
		playerForm.setImage(player.getImage());
		playerForm.setVersion(player.getVersion());

		return "admin/player/update/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "/player/update/confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/player/update/input";
		}

		// 確認画面の表示だけ
		return "admin/player/update/confirm";
	}

	/**
	 * 更新処理（DBに送る）
	 * 
	 * @return redirect
	 */
<<<<<<< HEAD
	@RequestMapping(value = "/player/update/update", method = { RequestMethod.POST })
	public String update(Model model,@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result,
=======
	@RequestMapping(value = "/player/update/complete", method = { RequestMethod.POST })
	public String update(@ModelAttribute("player") @Validated PlayerForm playerForm, BindingResult result,
>>>>>>> branch 'master' of git@github.com:keitataira1/funsite_manager.git
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/player/update/input";
		}

		// フォームからエンティティへの変換
		PlayerEntity player = new PlayerEntity();
		player.setId(playerForm.getId());
		player.setName(playerForm.getName());
		player.setBirthday(LocalDate.parse(playerForm.getBirthday()));
		player.setPosition(playerForm.getPosition());
		player.setComment(playerForm.getComment());
		player.setImage(playerForm.getImage());
		player.setVersion(playerForm.getVersion());

		try {
			// 更新処理
			playerUpdateService.update(player);
		} catch (OptimisticLockingFailureException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:../list";
		}catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			model.addAttribute("message", message);
			return "admin/player/update/input";
		}
		

		redirectAttrs.addFlashAttribute("player", playerForm);
		return "redirect:complete";

	}

	@RequestMapping(value = "/player/update/complete", method = { RequestMethod.GET })
	public String complete() {

		return "admin/player/update/complete";
	}

}
