package com.anazzubair.techy.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.anazzubair.techy.business.model.User;
import com.anazzubair.techy.business.service.UserServiceImpl;
import com.anazzubair.techy.web.config.RootApplicationContext;
import com.anazzubair.techy.web.config.ServletApplicationContext;
import com.anazzubair.techy.web.controller.IndexController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={ServletApplicationContext.class, RootApplicationContext.class})
public class IndexControllerTests {

	@InjectMocks
	IndexController controller = new IndexController();
	
	@Mock
	UserServiceImpl userService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}
	
	final static Logger logger = LoggerFactory.getLogger(IndexControllerTests.class);

	@Test
	public void index() throws Exception {
		
		final String expectedUsername = "expected user";
		User expectedUser = new User(expectedUsername);
		
		when(userService.getMeAUser()).thenReturn(expectedUser);
		mockMvc.perform(get("/index.html"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("user", expectedUser));
		
		verify(userService, times(1)).getMeAUser();
		verifyNoMoreInteractions(userService);
	}
}
