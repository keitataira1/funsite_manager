package jp.co.taxis.funsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.taxis.funsite.login.FansiteUserDetailService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FansiteUserDetailService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		// テスト用の暗号化しない場合
		return NoOpPasswordEncoder.getInstance();
		//return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/js/**", "/css/**", "/bootstrap/**", "/error", "/actuator/**").permitAll()
				.antMatchers("/**").authenticated();
		// ログイン
		http.formLogin().loginPage("/admin/login").loginProcessingUrl("/login_check").usernameParameter("user_id")
				.passwordParameter("password").defaultSuccessUrl("/admin/menu")
				.failureUrl("/admin/login?error=true").permitAll();
		// ログアウト機能
		http.logout().logoutUrl("/admin/logout").invalidateHttpSession(true).logoutSuccessUrl("/admin/login")
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}