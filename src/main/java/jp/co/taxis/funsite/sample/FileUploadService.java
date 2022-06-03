package jp.co.taxis.funsite.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.util.FileUtil;

/**
 * ファイルアップロードサービス.
 * @author tomal
 *
 */
@Service
public class FileUploadService {

	@Autowired
	private FileUtil fileUtil;

	public void saveFile(String fileName, byte[] file) {

		try {
			fileUtil.saveFile(fileName, file);
		} catch (Exception e) {
			throw new ApplicationException("file.upload.error", e);
		}

	}

}
