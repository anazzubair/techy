package com.anazzubair.techy.web.config;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.anazzubair.techy.business.model.MessageResource;
import com.anazzubair.techy.business.repository.MessageResourceRepository;
import com.anazzubair.techy.business.service.MessageResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={ServletApplicationContext.class, RootApplicationContext.class})
public class DatabaseMessageSourceWiringTests {

	@Inject
	MessageSource messageSource;
	
	@Inject
	MessageResourceService messageService;
	
	@Inject
	MessageResourceRepository messageRepository;
	
	private final String codeUnderTest = "user.username";
	private final String expectedResult = "somethingelse";
	
	@Before
	public void setup() {
		MessageResource resource = messageRepository.findByCode(codeUnderTest);
		if(resource != null) {
			messageRepository.delete(resource.getId());
		}
		messageRepository.save(new MessageResource(codeUnderTest, expectedResult, "somethingelsefr"));
	}
	
	@Test
	public void test_DatabaseMessageSource_is_being_called_for_resources(){
		String message = messageSource.getMessage("user.username", null, Locale.ENGLISH);
		assertEquals(expectedResult, message);
	}
}
