package com.example.paymytax.service.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.paymytax.controller.MainController;
import com.example.paymytax.dao.ZoneRepository;
import com.example.paymytax.entity.Zone;
import com.example.paymytax.exception.RecordNotFoundException;
import com.example.paymytax.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService {
	
	private final static Logger logger = LoggerFactory.getLogger(ZoneServiceImpl.class);
	
	@Autowired
	ZoneRepository zoneRepo;

	@Override
	@Transactional
	public List<Zone> getAllZones() throws RecordNotFoundException {
		try {
		List<Zone> zones = zoneRepo.findAll();
		if(zones.size()==0)
		{
			throw new RecordNotFoundException("No zones Data are  Stored in the Database Conact Admin");
		}
		return zones;
		}
		catch(RecordNotFoundException ex)
		{
			throw ex;
		}
		catch (Exception e) {
			throw new RecordNotFoundException("Failed to Fetch Zone Details",e.getCause());
		}
	}

}
