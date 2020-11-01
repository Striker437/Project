package com.smart.Controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.DAO.ContactRepository;
import com.smart.DAO.UserRepository;
import com.smart.Entities.Contact;
import com.smart.Entities.User;
import com.smart.Helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	//Function for adding common data to (response) dasshboard page and add contact form
	//this function is called before every method and return the model to page
	@ModelAttribute
	public void CommonData(Model model, Principal principle)
	{
	
		String username=principle.getName();
		System.out.println("USERNAME  "+username);
		User user=userRepository.getUserByUserName(username);
		
		System.out.println("USERNAME  "+user.toString());
		model.addAttribute("user", user);
		
	}
	
	//handler for User DashBoard Page
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principle)
	{
		
		
		
		
		
		return "normal/user_dashboard";
		
	}
	//handler for Add contact page
	@GetMapping("/add-contact")
	public String AddContactform(Model m)
	{
		m.addAttribute("contact", new Contact());
		m.addAttribute("title", "Add Contact");
		return "normal/add_contact_form";
		
		
	}
	
	//handler for process contact
	@PostMapping("/process-contact")
	public String ProcessContact(@Valid @ModelAttribute("contact")Contact contact,BindingResult result1,Principal principle,Model model,HttpSession session)
	{
		
		try
		{
		if(result1.hasErrors())
		{
			System.out.println("Error "+result1.toString());
			model.addAttribute("contact", contact);
			throw new Exception();
		}
		String name=principle.getName();
		//System.out.println("User Detail and Email  "+name);
		User UserByName=userRepository.getUserByUserName(name);
		contact.setUser(UserByName);
		UserByName.getContacts().add(contact);
		userRepository.save(UserByName);
		//System.out.println("Added"+contact.getName()+contact.getEmail());
		model.addAttribute("contact", new Contact());
		session.setAttribute("message", new Message("Successfully Added!!","Alert Sucess!!"));
		return "normal/add_contact_form";
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("contact", contact);
			session.setAttribute("message",new Message("Something Went Wrong !!", "Alert-Danger!!"));
			return "normal/add_contact_form";
		}
		
		//System.out.println("DATA  "+contact.toString());
		
		
		
	}

}
