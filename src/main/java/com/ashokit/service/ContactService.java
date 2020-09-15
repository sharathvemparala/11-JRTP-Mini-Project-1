package com.ashokit.service;

import java.util.List;

import com.ashokit.pojo.Contact;

public interface ContactService {
	
	public boolean saveContact(Contact contact);
	
	public List<Contact> getAllContacts();
	
	public Contact getContactById(Integer cId);
	
	public boolean updateContact(Contact contact);
	
	public boolean deleteContactById(Integer cId);

}
