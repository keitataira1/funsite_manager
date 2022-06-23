package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.form.ItemForm;
import jp.co.taxis.funsite.service.ItemDeleteService;

@Controller
@RequestMapping("admin")
public class ItemDeleteController {

	@Autowired
	private ItemDeleteService itemDeleteService;

	/**
	 * 入力画面表示メソッド.
	 */
	@RequestMapping(value = "item/delete", method = { RequestMethod.GET})
	public String confirm(@ModelAttribute("item") ItemForm itemForm, Model model) {

		ItemEntity item = itemDeleteService.getItem(itemForm.getId());
		itemForm.setId(item.getId());
		itemForm.setName(item.getName());
		itemForm.setPrice(item.getPrice());
		//itemForm.setGame(item.getGame());

		return "admin/item/delete";
	}

	/**
	 * 削除処理.
	 */
	@RequestMapping(value = "item/delete/delete", method = { RequestMethod.POST })
	public String delete(@ModelAttribute("item") ItemForm itemForm, Model model,
			RedirectAttributes redirectAttrs) {

		// 削除処理
		itemDeleteService.delete(itemForm.getId());
		
		redirectAttrs.addFlashAttribute("completeMessage", "削除が完了しました。");
		return "redirect:/admin/item/list";
	}

}
