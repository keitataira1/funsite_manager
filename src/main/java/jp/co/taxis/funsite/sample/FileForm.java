package jp.co.taxis.funsite.sample;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ファイルアップロード用Form.
 * @author tomal
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileForm {

	private MultipartFile file;

}
