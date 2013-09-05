package com.anazzubair.techy.business.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anazzubair.techy.business.model.MessageResource;
import com.anazzubair.techy.business.repository.MessageResourceRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class MessageResourceService {
	
	@Inject
	private MessageResourceRepository messageResourceRepository;
	
	public List<MessageResource> loadAllMessages() {
		return messageResourceRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public MessageResource addMessageResourceToStore(MessageResource messageResource) {
		return messageResourceRepository.saveAndFlush(messageResource);
	}
}
