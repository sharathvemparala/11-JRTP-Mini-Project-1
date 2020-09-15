package com.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.ContactDtlsEntity;
import com.ashokit.pojo.Contact;
import com.ashokit.repository.ContactInfoRepository;
@Service
public class ContactServiceImpl implements ContactService {

	
	@Autowired
	private ContactInfoRepository contactInfoRepository;
	
	
	@Override
	public boolean saveContact(Contact c) {
		
		ContactDtlsEntity entity =new ContactDtlsEntity();
		
		BeanUtils.copyProperties(c, entity);
		
		ContactDtlsEntity savedEntity = contactInfoRepository.save(entity);
		
		return savedEntity.getContactId()!=null;
	}

	@Override
	public List<Contact> getAllContacts() {
	

		List<Contact> contactsList=new ArrayList<Contact>();
		
		List<ContactDtlsEntity> entitiesList = contactInfoRepository.findAll();
		
		entitiesList.forEach(entity ->{
			
			Contact c=new Contact();
			BeanUtils.copyProperties(entity, c);
			contactsList.add(c);
		});
		
		
		return contactsList;
	}

	@Override
	public Contact getContactById(Integer cId) {
		
		Optional<ContactDtlsEntity> optional = contactInfoRepository.findById(cId);
		
		if(optional.isPresent()) {
			
			ContactDtlsEntity entity = optional.get();
			Contact c=new Contact();
			BeanUtils.copyProperties(entity, c);
			return c;
		}
		
		return null;
	}

	@Override
	public boolean updateContact(Contact contact) {
		
		return false;
	}

	@Override
	public boolean deleteContactById(Integer cId) {
		
		contactInfoRepository.deleteById(cId);
		return true;
	}

}
