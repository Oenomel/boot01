package ex.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ex.boot.config.ApplicationContext;

@EnableWebMvc
@EnableWebMvcSecurity
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Launcher extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(ApplicationContext.class);
	}
	
	public static void main(String[] args)
	{
		SpringApplication.run(Launcher.class, args);
	}
}
