package com.anazzubair.techy.web.config;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.context.support.AbstractMessageSource;

import com.anazzubair.techy.business.model.MessageResource;
import com.anazzubair.techy.business.service.MessageResourceService;

@Resource
public class DatabaseMessageSource extends AbstractMessageSource {

	@Inject
	MessageResourceService messageResourceService;

	private Map<String, Map<Locale, String>> messageResourceMap = new HashMap<>();
	
	@PostConstruct
	public void loadMessageResources() {
		synchronized (messageResourceMap) {
			messageResourceMap.clear();
			messageResourceMap.putAll(loadResources());
		}
	}
	
	private Map<String, Map<Locale, String>> loadResources() {
		Map<String, Map<Locale, String>> resourceMap = new HashMap<>();
		List<MessageResource> messageResources = messageResourceService.loadAllMessages();
		for(MessageResource messageResource : messageResources) {
			Map<Locale,String> localeMap = new HashMap<>();
			localeMap.put(Locale.ENGLISH, messageResource.getEnglish());
			localeMap.put(Locale.FRENCH, messageResource.getFrench());
			resourceMap.put(messageResource.getCode(), localeMap);
		}
		return resourceMap;
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String message = getMessageText(code, locale);
		if(message == null) {
			addResourceToStore(code, locale);
			return new MessageFormat(code, locale);
		}
		return new MessageFormat(message, locale);
	}
	
	@Override
	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		String message =  getMessageText(code, locale);
		if(message == null) {
			addResourceToStore(code, locale);
			return code;
		}
		return message;
	}

	private String getMessageText(String code, Locale locale) {
		synchronized (messageResourceMap) {
			Map<Locale, String> resource = this.messageResourceMap.get(code);
			return (resource != null) ? resource.get(locale) : null;
		}
	}
	
	private MessageResource addResourceToStore(String code, Locale locale) {
		MessageResource resource = new MessageResource(code, code, code);
		return messageResourceService.addMessageResourceToStore(resource);
	}
}
