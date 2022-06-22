package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchForm {
		
		@NotEmpty
		@Pattern(regexp = ".{1,20}")
		private String searchWord;
		
		@NotEmpty
		@Pattern(regexp = ".{1,20}")
		private String searchMatchTeam;
		
		@NotEmpty
		@Pattern(regexp = ".{1,20}")
		private String searchItem;
		
		

}
