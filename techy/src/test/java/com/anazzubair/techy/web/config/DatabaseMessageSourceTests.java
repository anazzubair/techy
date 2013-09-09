package com.anazzubair.techy.web.config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.anazzubair.techy.business.model.MessageResource;
import com.anazzubair.techy.business.service.MessageResourceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseMessageSourceTests {

	@Mock
	MessageResourceServiceImpl messageResourceService;
	
	@Mock
	MessageResource messageResource;

	@InjectMocks
	DatabaseMessageSource messageSource = new DatabaseMessageSource();
	
	private List<MessageResource> resources = Arrays.asList(
			  new MessageResource("user.username", "Username", "Usernom"),
			  new MessageResource("user.name", "Name", "Nom"),
			  new MessageResource("user.company", "Company", "CompanyFrench"));
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void verify_resolveCode_gets_returns_correct_messageformat(){
		
		when(messageResourceService.loadAllMessages()).thenReturn(resources);
		messageSource.loadMessageResources();
		MessageFormat expectedMessageFormat = new MessageFormat("Username", Locale.ENGLISH);
		MessageFormat messageFormat = messageSource.resolveCode("user.username", Locale.ENGLISH); 
		assertEquals(expectedMessageFormat, messageFormat);
		
		verify(messageResourceService, times(1)).loadAllMessages();
		verifyNoMoreInteractions(messageResourceService);
	}
	
	@Test
	public void verify_resolveCodeWithoutArguments_returns_correct_message(){
		
		when(messageResourceService.loadAllMessages()).thenReturn(resources);
		messageSource.loadMessageResources();
		String expectedMessage = "Username";
		String actualMessage = messageSource.resolveCodeWithoutArguments("user.username", Locale.ENGLISH); 
		assertEquals(expectedMessage, actualMessage);
		
		verify(messageResourceService, times(1)).loadAllMessages();
		verifyNoMoreInteractions(messageResourceService);
	}
	
	@Test
	public void verify_resolveCode_when_message_not_exists_it_is_added(){
		String code = "something.not";
		MessageResource resourceToAdd = new MessageResource(code, code, code);
		
		when(messageResourceService.loadAllMessages()).thenReturn(resources);
		when(messageResourceService.addMessageResourceToStore(resourceToAdd)).thenReturn(resourceToAdd);
		messageSource.loadMessageResources();
		
		MessageFormat expectedMessageFormat = new MessageFormat(code, Locale.ENGLISH);
		MessageFormat messageFormat = messageSource.resolveCode(code, Locale.ENGLISH); 
		assertEquals(expectedMessageFormat, messageFormat);
		
		verify(messageResourceService, times(1)).loadAllMessages();
		verify(messageResourceService, times(1)).addMessageResourceToStore(resourceToAdd);
		verifyNoMoreInteractions(messageResourceService);
	}
	
	@Test
	public void verify_resolveCodeWithoutArguments_when_message_not_exists_it_is_added(){
		String code = "something.not";
		MessageResource resourceToAdd = new MessageResource(code, code, code);
		
		when(messageResourceService.loadAllMessages()).thenReturn(resources);
		when(messageResourceService.addMessageResourceToStore(resourceToAdd)).thenReturn(resourceToAdd);
		messageSource.loadMessageResources();
		
		String expectedMessage = code;
		String actualMessage = messageSource.resolveCodeWithoutArguments(code, Locale.ENGLISH); 
		assertEquals(expectedMessage, actualMessage);
		
		verify(messageResourceService, times(1)).loadAllMessages();
		verify(messageResourceService, times(1)).addMessageResourceToStore(resourceToAdd);
		verifyNoMoreInteractions(messageResourceService);
	}
}
