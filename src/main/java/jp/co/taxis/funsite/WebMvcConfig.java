package jp.co.taxis.funsite;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.taxis.funsite.util.FileUtil;

/**
 * SpringMVC設定コード.
 * @author tomal
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * urlでアクセスするとlocalPathのフォルダにアクセスするように設定する.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// urlでアクセスするとlocalPathのフォルダにアクセスするように設定する。
		String url = "/upload_img/**";
		String localPath = "file:" + FileUtil.FILE_PATH;

		registry.addResourceHandler(url).addResourceLocations(localPath);
	}

}
