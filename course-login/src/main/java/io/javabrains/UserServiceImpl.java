package io.javabrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new UsernameNotFoundException("Username is empty");
        }

        User user = userRepository.findByName(name);
        if (user == null)
        {
            throw new UsernameNotFoundException("Username　name is empty");
        }

        return user;
    }
	
	@Autowired
    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}
