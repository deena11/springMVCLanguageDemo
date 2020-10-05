package com.example.paymytax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.paymytax.entity.UserTax;
import com.example.paymytax.enums.userStatus;
import com.example.paymytax.exception.RecordNotAddedException;
import com.example.paymytax.exception.RecordNotFoundException;
import com.example.paymytax.exception.TaxCalculationException;
import com.example.paymytax.service.UAVService;
import com.example.paymytax.service.UserTaxService;
import com.example.paymytax.service.ZoneService;

@Controller
public class MainController {
	
	private final static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UAVService uavInterface;
	
	@Autowired
	UserTaxService userTax;
	
	@Autowired
	ZoneService zoneInterface;

	@GetMapping("/greet")
	public String greetings()
	{
		return "index";
	}
	
	@PostMapping(value ="/userForm")
	@Validated
	public String storeUserData(ModelMap map, @Valid @ModelAttribute("UserTax") UserTax user,BindingResult result) throws RecordNotFoundException, RecordNotAddedException
	{
		logger.info("Entered into userForm Call");
		if(result.hasErrors())
		{
			logger.error("Request data failed to meet the Requirement validation failed");
			
				map.addAttribute("descriptionList", uavInterface.getAllDescriptions());
				map.addAttribute("zoneList",zoneInterface.getAllZones());
			
			
			List<String> statusList = new ArrayList<>();
			statusList.add(userStatus.Owner.toString());
			statusList.add(userStatus.Tenanted.toString());
			map.addAttribute("statusList",statusList);
			return "userForm";
		}
		
		logger.info(userTax.addUserTax(user));
		map.addAttribute("message",messageSource.getMessage("app.message", null, LocaleContextHolder.getLocale()));
		logger.info("Exiting  userForm Call");
		return "index";
	}
	
	@GetMapping("/user")
	public String redirectToForm(ModelMap map) throws RecordNotFoundException
	{
		logger.info("Entered into redirectTo from ");
		
			map.addAttribute("descriptionList", uavInterface.getAllDescriptions());
			map.addAttribute("zoneList",zoneInterface.getAllZones());
			List<String> statusList = new ArrayList<>();
			statusList.add(userStatus.Owner.toString());
			statusList.add(userStatus.Tenanted.toString());
			map.addAttribute("statusList",statusList);
			map.addAttribute("message", "HelloWorld");
			
		map.addAttribute("UserTax", new UserTax()); 
		logger.info("Exiting the redirectTo from ");
		return "userForm";
	}
	
	@GetMapping("/report")
	public String redirectToZonalReport()
	{
		logger.info("Entered into redirect to zonal Report Call");
		return "zonal";
		
	}
	
	@PostMapping("/calculateTax")
	public <T> ResponseEntity<?> calculateTax(UserTax user) throws TaxCalculationException 	{
		
		Map<String, Object> list = new HashMap<>();
		
		
			int result = uavInterface.calculateTax(user);
			list.put("status","SUCCESS");
			list.put("result", result);
			return ResponseEntity.status(HttpStatus.OK).body(list);
			
		
	}
	
	@GetMapping("/reportData")
	public String reportData(ModelMap map,@RequestParam("year") Integer year) throws TaxCalculationException, RecordNotFoundException
	{
		System.out.println("Ajax call is called"+year);
		map.addAttribute("message",messageSource.getMessage("zonal.table.header", null, LocaleContextHolder.getLocale()) +" " +year);
		map.addAttribute("results",userTax.fetchAllUserTaxDetails(year));
		return "zonal";
	}
	
//	@GetMapping("/errorPage/{message}")
//	public String errorPage(@PathVariable String message,ModelMap map)
//	{
//		logger.info("Entered into ErrorPage Redirection call ");
//		map.addAttribute("errorMessage", message);
//		map.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
//		map.addAttribute("statusMessage", "Internal Server Error Please Retry or contact the ADMIN");
//		
//		return "errorPage";
//	}
}
