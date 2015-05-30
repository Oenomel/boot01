package ex.boot.config;

import org.springframework.stereotype.Component;

@Component
public class DBProperties
{
	private final String url = "jdbc:mysql://localhost:3306/test";
	private final String username = "root";
	private final String password = "root";
	
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
