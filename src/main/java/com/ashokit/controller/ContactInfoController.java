package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashokit.pojo.Contact;
import com.ashokit.service.ContactService;

@Controller
public class ContactInfoController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping("/")
	public String loadContactForm(Model model) {

		model.addAttribute("contact", new Contact());

		return "index";
	}

	
	  @PostMapping("/saveContact")
	  public String handleSubmitBtn(@ModelAttribute("contact") Contact c,RedirectAttributes attributes) {
	  
		  boolean saveContact = contactService.saveContact(c);
		  
		  if(saveContact) {
			  
			  
			  if(c.getContactId()!=null) {
				  
				  attributes.addFlashAttribute("succMsg", "Updated Contact Sucessfully");
			  }else {
			  
			 attributes.addFlashAttribute("succMsg", "Contact Saved Succesesfully");
			 
			  }
		  }else {
			  
			  attributes.addFlashAttribute ("errMsg", "Failed To Save Contact");
		  }
	  
		  return "redirect:/submitSucessfully"; 
	  
	  }
	  
	  @GetMapping("/submitSucessfully")
	  public String submitContactSuccessfully(Model model) {
		  
		  model.addAttribute("contact", new Contact());
		
		  return "index";
		  
	  }
	  
	  @GetMapping("/viewContacts")
	  public String viewAllContcatsHyperLink(Model model) {
		
		  List<Contact> allContacts = contactService.getAllContacts();
		  
		  model.addAttribute("contacts", allContacts);
		  
		  return "viewContacts";
		  
		  
	  }
	  
	 

}
