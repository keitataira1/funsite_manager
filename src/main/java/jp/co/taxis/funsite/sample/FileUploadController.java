package jp.co.taxis.funsite.sample;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jp.co.taxis.funsite.exception.ApplicationException;

/**
 * ファイルアップロードサンプルコントローラ.
 * <p>
 *  ファイルのアップロードを行うサンプル。
 * </p>
 * @author tomal
 *
 */
@Controller
@RequestMapping("upload")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	MessageSource messageSource;

	@GetMapping
	public String init(@ModelAttribute("file") FileForm imgForm) {
		return "sample/img_upload_input";
	}

	@PostMapping
	public String upload(@ModelAttribute("file") FileForm imgForm, Model model) {

		// ファイル名取得
		MultipartFile file = imgForm.getFile();
		String fileName = file.getOriginalFilename();

		// 拡張子のチェック
		String extention = fileName.substring(fileName.lastIndexOf("."));
		if (!extention.contentEquals(".jpg") &&  !extention.contentEquals(".png")) {
			model.addAttribute("message", messageSource.getMessage("file.extention.error", null, Locale.getDefault()));
			return "sample/img_upload_input";
		}

		try {
			// 保存処理
			fileUploadService.saveFile(fileName, file.getBytes());
		} catch (ApplicationException e) {
			e.printStackTrace();
			model.addAttribute("message", messageSource.getMessage(e.getMessage(), null, Locale.getDefault()));
			return "sample/img_upload_input";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("message", messageSource.getMessage("file.upload.error", null, Locale.getDefault()));
			return "sample/img_upload_input";
		}

		model.addAttribute("fileName", fileName);
		return "sample/img_upload_complete";
	}

}
