package com.smart.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.DAO.ContactRepository;
import com.smart.DAO.UserRepository;
import com.smart.Entities.Contact;
import com.smart.Entities.User;
import com.smart.Helper.Message;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository  userRepository;
	
	
	
	
	  @Autowired
	  private PasswordEncoder passwordEncoder;
	 
	
	//Home Page Handler
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}
	
	
	
	
	@GetMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "about";
	}
	
	
	//Signup Page
	@GetMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	//Handler for Registering user
		@PostMapping("/do_register")
		public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result1,@RequestParam(value = "agreement", defaultValue  =  "false") boolean agreement , Model model,HttpSession session)
		{
			try {
				if(!agreement)
				{
					System.out.println("you have not agreed to terms and conditions");
					throw new Exception("you have not agreed to terms and conditions");
				}
				if(result1.hasErrors())
				{
					System.out.println("Error "+result1.toString());
					model.addAttribute("user", user);
					return "signup";
				}
				user.setRole("ROLE_USER");
				user.setEnabled(true);
				user.setImageUrl("default.png");
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				User result=userRepository.save(user);
				
				
				model.addAttribute("user", new User());
				session.setAttribute("message", new Message("Successfully Registered!!","Alert Sucess!!"));
				
				//System.out.println("Agreement"+ agreement);
				System.out.println("User"+user);
				return "signup";
				
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("user", user);
				session.setAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(), "Alert-Danger!!"));
				return "signup";
			}
			
			
			
		}

		
		
		//handler for Login Page
		@GetMapping("/signin")
		public String Login()
		{
			return "login";
		}
		
		
		
		
	
	
	

}
