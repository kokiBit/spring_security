package io.javabrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistController {
	
	@Autowired
	private UserRepository userRepisitory;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/regist")
	public String createUser() {
		return "regist";
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/regist")
	public String create(Model model, @ModelAttribute UserForm userForm) {
		User user = new User();
		user.setName(userForm.getName());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		userRepisitory.save(user);
		return "redirect:/login";
	}
}
