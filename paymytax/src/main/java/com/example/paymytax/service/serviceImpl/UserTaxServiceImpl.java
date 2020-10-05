package com.example.paymytax.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.paymytax.controller.MainController;
import com.example.paymytax.dao.UserTaxRepository;
import com.example.paymytax.entity.UserTax;
import com.example.paymytax.entity.Zone;
import com.example.paymytax.enums.userStatus;
import com.example.paymytax.exception.RecordNotAddedException;
import com.example.paymytax.exception.RecordNotFoundException;
import com.example.paymytax.service.UserTaxService;
import com.example.paymytax.service.ZoneService;

@Service
@Transactional
public class UserTaxServiceImpl implements UserTaxService{
	
	private final static Logger logger = LoggerFactory.getLogger(UserTaxServiceImpl.class);
	
	@Autowired
	UserTaxRepository userTaxRepo;
	
	@Autowired
	ZoneService zoneInterface;

	public String addUserTax(UserTax user) throws RecordNotAddedException {
		try
		{
			userTaxRepo.save(user);
			return "Successfully stored ";
		}
		catch(Exception e)
		{
			throw new RecordNotAddedException("Failed to Store Data",e.getCause());
		}
	}

	public Map<String,Map<String,Double>> fetchAllUserTaxDetails(int year) throws RecordNotFoundException {
		try {
			List<UserTax> results = userTaxRepo.getUserTaxByYear(year);
			List<Zone> zones = zoneInterface.getAllZones();
		
		if(results.size()==0)
		{
			throw new RecordNotFoundException("No Record Found For the Year " +year);
		}
		logger.info("************************************************");
		logger.info(results.toString());
		Map<String,Map<String,Double>> report = new HashMap<String, Map<String,Double>>();
		zones.forEach(zone->{
			Map<String,Double> statusValue = new HashMap<>();
			Double sum = 0.0;
			sum = results.stream().filter(user -> user.getStatus().equals(userStatus.Owner))
					.filter(user -> user.getZone().getZoneName().equals(zone.getZoneName()))
					.map(value -> value.getTotalTax())
					.reduce(0.0,(a,b) -> a + b);
			statusValue.put("Owner", sum);
			
			sum = results.stream().filter(user -> user.getStatus().equals(userStatus.Tenanted))
					.filter(user -> user.getZone().getZoneName().equals(zone.getZoneName()))
					.map(value -> value.getTotalTax())
					.reduce(0.0,(a,b) -> a+b);
			statusValue.put("Tenent", sum);
			report.put(zone.getZoneName(), statusValue);
		});
		
		logger.info("************************************************");
		
		logger.info(report.toString());
		return report;
	
		}
		catch (RecordNotFoundException ex)
		{
			throw ex;
		}
		catch(Exception ex)
		{
			throw new RecordNotFoundException("Failed to Fetch Records From DataBases");
		}
		};
	
	

}
