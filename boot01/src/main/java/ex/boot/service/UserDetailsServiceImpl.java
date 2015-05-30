package ex.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ex.boot.domain.User;
import ex.boot.persistence.UserMapper;

public class UserDetailsServiceImpl implements UserDetailsService
{	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUsersByUsername(String username) throws UsernameNotFoundException
	{
		User user;
		user.setName(username);
		
		User result = null;
		try
		{
			result = userMapper.getUser(user);
			
			if(result == null)
			{
				throw new UsernameNotFoundException("Not found user " + username);
			}
		}
		catch (Exception e)
		{
			throw new UsernameNotFoundException("Database Exception");
		}
	}
}
