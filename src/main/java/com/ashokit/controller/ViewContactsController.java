package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashokit.pojo.Contact;
import com.ashokit.service.ContactService;

@Controller
public class ViewContactsController {
	
	
	@Autowired
	private  ContactService  contactService;
	
	@GetMapping("/editContact")
	public String editContact(@RequestParam("cid")Integer contactId,Model model) {
		
		Contact contact = contactService.getContactById(contactId);
		
		model.addAttribute("contact", contact);
		
		return "index";
	}
	
	@GetMapping("/deleteContact")
	public String deleteContact(@RequestParam("cid") Integer contactId,RedirectAttributes attributes) {
		
		  contactService.deleteContactById(contactId);
		  
		  attributes.addFlashAttribute("delSucc", "Contact Deleted Sucessfully");
		return "redirect:viewContacts";
		
		
	}
	
	

}
