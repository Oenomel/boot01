package ex.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties security;
	
	@Autowired
	private LoginFailure loginFailure;
	
	@Autowired
	DataSourceConfiguration dataSource;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().failureUrl("/?error")
        	.loginProcessingUrl("/login")
        	.loginPage("/")
        	.usernameParameter("username")
        	.passwordParameter("password")
        	.permitAll()
        	.and()
        	.logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	.logoutSuccessUrl("/?logout")
        	.invalidateHttpSession(true)
        	.permitAll()
        	.and()
        	.exceptionHandling().accessDeniedPage("/login")
        	.and()
        	.csrf();
    }
 
	@Autowired
    protected void authenticationConfig(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(authenticationProvider)
        auth.jdbcAuthentication().dataSource(dataSource.dataSource())
        	.usersByUsernameQuery("SELECT username, password, name FROM test WHERE username=?")
        	.authoritiesByUsernameQuery("SELECT username, name, role FROM test WHERE username=?");
    }

}
