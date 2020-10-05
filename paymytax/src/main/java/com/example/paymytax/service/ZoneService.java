package com.example.paymytax.service;

import java.util.List;

import com.example.paymytax.entity.Zone;
import com.example.paymytax.exception.RecordNotFoundException;

public interface ZoneService {
	
	public List<Zone> getAllZones() throws RecordNotFoundException;

}
