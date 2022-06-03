package jp.co.taxis.funsite.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

/**
 * ファイルユーティリティクラス.
 * @author tomal
 *
 */
@Component
public class FileUtil {

	public static final String FILE_PATH = "E:\\workspace\\temp\\";

	/**
	 * ファイル保存メソッド.
	 * @param fileName ファイル名
	 * @param file ファイル
	 * @throws IOException ファイル処理中に発生する例外
	 */
	public void saveFile(String fileName, byte[] file) throws IOException {

		File uploadFile = new File(FileUtil.FILE_PATH + fileName);
		try (BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));) {
			uploadFileStream.write(file);
		}
	}

}
