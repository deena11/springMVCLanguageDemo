package com.example.paymytax.controller;


import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.NestedServletException;

import com.example.paymytax.controller.MainController;
import com.example.paymytax.dao.UAVRepository;
import com.example.paymytax.dao.UserTaxRepository;
import com.example.paymytax.dao.ZoneRepository;
import com.example.paymytax.entity.UserTax;
import com.example.paymytax.entity.Zone;
import com.example.paymytax.enums.userStatus;
import com.example.paymytax.exception.RecordNotFoundException;
import com.example.paymytax.service.UAVService;
import com.example.paymytax.service.UserTaxService;
import com.example.paymytax.service.ZoneService;
import com.example.paymytax.service.serviceImpl.UAVServiceImpl;
import com.example.paymytax.service.serviceImpl.UserTaxServiceImpl;
import com.example.paymytax.service.serviceImpl.ZoneServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {MainController.class,UserTaxServiceImpl.class,UAVServiceImpl.class,ZoneServiceImpl.class})
public class MainControllerTest {
	
	private MockMvc mockMVC;
	
	@Autowired
	private MainController mainController;
	
	@Autowired
	UserTaxService userService;
	
	@Autowired
	ZoneService zoneService;
	
	@Autowired
	UAVService uavService;
	
	@MockBean
	ZoneRepository zoneRepo;
	
	@MockBean
	UAVRepository uavRepo;
	
	@MockBean
	UserTaxRepository userTaxRepo;
	
	private MessageSource messageSource;
	
	@Autowired
	private DelegatingMessageSource delegatingMessageSource;
	
	@Before
	public void setup()	{

		
		messageSource = Mockito.mock(MessageSource.class);
		Mockito.when(messageSource.getMessage(Mockito.anyString(),Mockito.any(Object[].class),Mockito.any(Locale.class))).thenReturn("");
		  delegatingMessageSource.setParentMessageSource(messageSource);
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		
		mockMVC = MockMvcBuilders.standaloneSetup(mainController).setViewResolvers(resolver).build();
	}
	
	@Test
	public void greetTest() throws Exception	{
		this.mockMVC.perform(get("/greet")).andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(forwardedUrl("/WEB-INF/view/index.jsp"));
	}
	
	@Test
	public void validUserFormTest() throws Exception	{
		
		
		
		Mockito.when(userTaxRepo.save(getUser())).thenReturn(getUser());
		
		this.mockMVC.perform(post("/userForm")
				.flashAttr("UserTax", getUser())
				.accept(MediaType.ALL))
		.andExpect(view().name("index"))
		.andExpect(forwardedUrl("/WEB-INF/view/index.jsp"));
	}

	
	@Test
	public void notValidUserFormTest() throws Exception	{
		
		
		
		Mockito.when(uavRepo.findAllDescriptions()).thenReturn(getDescriptionList());
		Mockito.when(zoneRepo.findAll()).thenReturn(getZones());
		
		UserTax user = getUser();
		user.setName("s");
		this.mockMVC.perform(post("/userForm")
				.flashAttr("UserTax", user)
				.accept(MediaType.ALL))
		.andExpect(view().name("userForm"))
		.andExpect(forwardedUrl("/WEB-INF/view/userForm.jsp"));
	}
	
	@Test(expected=NestedServletException.class)
	public void notValidUserFormTestException() throws Exception	{
		
		
		
		Mockito.when(uavRepo.findAllDescriptions()).thenReturn(getDescriptionList());
		Mockito.when(zoneRepo.findAll()).thenReturn(new ArrayList<Zone>());
		
		UserTax user = getUser();
		user.setName("s");
		this.mockMVC.perform(post("/userForm")
				.flashAttr("UserTax", user)
				.accept(MediaType.ALL));
	}
	
	@Test
	public void testRedirectToForm() throws Exception	{
		Mockito.when(uavRepo.findAllDescriptions()).thenReturn(getDescriptionList());
		Mockito.when(zoneRepo.findAll()).thenReturn(getZones());
		
		this.mockMVC.perform(get("/user"))
		.andExpect(view().name("userForm"))
		.andExpect(forwardedUrl("/WEB-INF/view/userForm.jsp"));
	
		
	}
	
	@Test
	public void testRedirectToZonalReport() throws Exception	{
		
		this.mockMVC.perform(get("/report"))
		.andExpect(view().name("zonal"))
		.andExpect(forwardedUrl("/WEB-INF/view/zonal.jsp"));
	
		
	}
	
	@Test
	public void testReportData() throws Exception	{
		
		Mockito.when(userTaxRepo.getUserTaxByYear(Mockito.anyInt())).thenReturn(getUserList());
		Mockito.when(zoneRepo.findAll()).thenReturn(getZones());
		
		this.mockMVC.perform(get("/reportData").param("year", "1990"))
		.andExpect(view().name("zonal"))
		.andExpect(forwardedUrl("/WEB-INF/view/zonal.jsp"));
	
		
	}
	
	@Test
	public void testErrorPage() throws Exception	{
		
		
		this.mockMVC.perform(get("/errorPage/helloworld"))
		.andExpect(view().name("errorPage"))
		.andExpect(forwardedUrl("/WEB-INF/view/errorPage.jsp"));
	
		
	}
	
	
	@Test
	public void testCalculateTax() throws Exception	{
		
		Mockito.when(uavRepo.getUnitAreaValue(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(1000);

		ResponseEntity<?> result = mainController.calculateTax(getUser());
		assertNotNull(result);
	}
	
	public String getJsonString(Object value)
	{
		try {
			return new ObjectMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			 throw new RuntimeException(e);
		}
	}
	
	public UserTax getUser()
	{
		UserTax user = new UserTax();
		user.setYearOfAssessment(2014);
		user.setConstructedYear(2000);
		user.setStatus(userStatus.Owner);
		user.setName("test");
		user.setTotalTax(20000d);
		user.setZone(getZone());
		user.setAddress("test SampleAddresss");
		user.setArea(1200);
		user.setDescription("sample Descriptions");
		user.seteMail("testmail@mail.com");
		return user;
		
	}
	
	public Zone getZone()
	{
		return new Zone(1,"ZoneA");
	}
	
	
	public List<String> getDescriptionList()
	{
		List<String> descriptions = new ArrayList<>();
		descriptions.add("test1");
		return descriptions;
		
	}
	
	public List<Zone> getZones()
	{
		List<Zone> zones = new ArrayList<>();
		zones.add(getZone());
		return zones;
		
	}
	
	public List<UserTax> getUserList()
	{
		List<UserTax> userList = new ArrayList<>();
		userList.add(getUser());
		return userList;
	}
	
	

}
