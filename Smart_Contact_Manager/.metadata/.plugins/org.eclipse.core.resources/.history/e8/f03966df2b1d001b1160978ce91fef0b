package com.smart.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
		String username=principle.getName();  //principal is used to fetch the username on the basis of login id
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
	public String ProcessContact(@Valid @ModelAttribute("contact") Contact contact , BindingResult result1   , Principal principle , Model model , HttpSession session ,@RequestParam("profileImage") MultipartFile file)
	{
		
		try
		{
		if(result1.hasErrors())
		{
			System.out.println("Error "+result1.toString());
			model.addAttribute("contact", contact);
			System.out.println("Validation error in cotact form");
			throw new Exception("Please enter blank field");
		}
		String name=principle.getName();
		//System.out.println("User Detail and Email  "+name);
		User UserByName=userRepository.getUserByUserName(name);
		
		
		//processing and uploading image to img folder and saving name to database
		
		if(file.isEmpty())
		{
			
			//if the file is empty then try this message
			System.out.println("file is empty");
		}
		
		else
		{
			
			//copy  the file to folder and update the name to contact
			contact.setImage(file.getOriginalFilename());
			
			File saveFile=new ClassPathResource("static/img").getFile();
			
			System.out.println("file path"+saveFile);
			
			Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			System.out.println("path aise hi"+path);
			
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			
			System.out.println("Image is uploaded");
			
			
		}
		
		//Ends
		
		
		
		
		contact.setUser(UserByName);     //setting user id value in contact table
		UserByName.getContacts().add(contact);   //adding contact 
		userRepository.save(UserByName);   //adding contact to database
		System.out.println("Added to database");
		model.addAttribute("contact", new Contact());
		session.setAttribute("message", new Message("Your contact is Added!!","Alert Sucess!!"));
		System.out.println("Contact successfully added");
		return "normal/add_contact_form";
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("contact", contact);
			session.setAttribute("message",new Message("Something Went Wrong !!  "+e.getMessage(), "Alert-Danger!!"));
			return "normal/add_contact_form";
		}
		
		//System.out.println("DATA  "+contact.toString());
		
		
		
	}

}
