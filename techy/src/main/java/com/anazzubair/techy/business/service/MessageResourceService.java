package com.anazzubair.techy.business.service;

import java.util.List;

import com.anazzubair.techy.business.model.MessageResource;

public interface MessageResourceService {

	List<MessageResource> loadAllMessages();

	MessageResource addMessageResourceToStore(MessageResource messageResource);

}