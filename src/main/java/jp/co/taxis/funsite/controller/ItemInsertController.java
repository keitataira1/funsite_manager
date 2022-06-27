package jp.co.taxis.funsite.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.ItemForm;
import jp.co.taxis.funsite.sample.FileUploadService;
import jp.co.taxis.funsite.service.GameInsertService;
import jp.co.taxis.funsite.service.ItemInsertService;

@Controller
@RequestMapping("admin")
public class ItemInsertController {
	
	@Autowired
	private ItemInsertService itemInsertService;
	
	@Autowired
	private GameInsertService gameInsertService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	MessageSource messageSource;

	/**
	 * 入力画面表示メソッド.
	 * 
	 * @return input.htmlにリターン
	 */
	@RequestMapping(value = "/item/insert/input", method = { RequestMethod.GET, RequestMethod.POST })
	public String input(Model model,@ModelAttribute("item") ItemForm itemForm) {
		
		List<GameEntity> gameList = gameInsertService.getGameAll();
	    model.addAttribute("gameList", gameList);
	    
		// 入力画面を出すだけ
		return "admin/item/insert/input";
	}

	/**
	 * 確認画面表示メソッド.
	 * 
	 * @return confirm.htmlにリターン
	 */
	@RequestMapping(value = "/item/insert/confirm", method = { RequestMethod.POST })
	public String confirm(Model model,@ModelAttribute("item") @Validated ItemForm itemForm, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/item/insert/input";
		}
		
		// ファイル名取得
				MultipartFile file = itemForm.getImage();
				String fileName = file.getOriginalFilename();
				itemForm.setImageFileName(fileName);

				if (!(itemForm.getImageFileName().equals(""))) {

					// 拡張子のチェック
					String extention = fileName.substring(fileName.lastIndexOf("."));
					if (!extention.contentEquals(".jpg") && !extention.contentEquals(".png")) {
						String message = messageSource.getMessage("file.extention.error", null, Locale.getDefault());
						model.addAttribute("message", message);
						return "admin/player/insert/input";
					}

					try {
						// 保存処理
						fileUploadService.saveFile(fileName, file.getBytes());
					} catch (ApplicationException e) {
						e.printStackTrace();
						String messageKey = e.getMessage();
						String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
						model.addAttribute("message", message);
						return "admin/player/insert/input";
					} catch (IOException e) {
						e.printStackTrace();
						String messageKey = e.getMessage();
						String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
						model.addAttribute("message", message);
						return "admin/player/insert/input";
					}
				}
		
		// 確認画面の表示だけ
		return "admin/item/insert/confirm";
	}

	/**
	 * 登録入力画面（DBに送る）
	 * 
	 * @return redirect
	 */
	@RequestMapping(value = "/item/insert/complete", method = { RequestMethod.POST })
	public String insert(@ModelAttribute("item") @Validated ItemForm itemForm, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/item/insert/input";
		}

		// フォームからエンティティへの変換
		ItemEntity item = new ItemEntity();
		item.setId(itemForm.getId());
		item.setName(itemForm.getName());
		item.setItemExplain(itemForm.getItemExplain());
		item.setPrice(itemForm.getPrice());
		item.setGameId(itemForm.getGameId());

		// 登録処理

		try {
			// 更新処理
			itemInsertService.insert(item);
			itemForm.setId(item.getId());
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:../list";
		}

		redirectAttrs.addFlashAttribute("item", itemForm);
		return "redirect:complete";

	}

	/**
	 * 登録完了画面
	 */
	@RequestMapping(value = "/item/insert/complete", method = { RequestMethod.GET })
	public String complete() {
		// 画面を表示するだけなので処理はなし。
		return "admin/item/insert/complete";
	}

}
