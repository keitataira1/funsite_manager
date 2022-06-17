package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchForm {
		
		//半角大文字などの入力チェック必要
		@NotNull
		@Pattern(regexp = ".{1,20}")
		private String searchWord;
		
		@NotNull
		@Pattern(regexp = ".{1,20}")
		private String searchMatchTeam;
		
		@NotNull
		@Pattern(regexp = ".{1,20}")
		private String searchItem;
		
		

}
