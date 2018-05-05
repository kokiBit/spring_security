package io.javabrains.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import io.javabrains.LoginController;

/**
 * Spring Security設定クラス.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.authorizeRequests().antMatchers("/regist").permitAll().anyRequest().authenticated();
        httpSecurity.formLogin().loginPage(LoginController.PAGE).usernameParameter("user")
                .passwordParameter("pass").permitAll();
    }
	 
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception
    {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService);
    }
    
//    @Configuration
//    protected static class AuthenticationConfiguration
//    extends GlobalAuthenticationConfigurerAdapter {
//        @Autowired
//        UserServiceImpl userDetailsService;
//
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            // 認証するユーザーを設定する
//            auth.userDetailsService(userDetailsService)
//            // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
//            .passwordEncoder(new BCryptPasswordEncoder());
//
//        }
//    }
//    
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
    
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }
}
