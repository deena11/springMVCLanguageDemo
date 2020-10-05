package com.example.paymytax.service;

import java.util.List;

import com.example.paymytax.entity.UserTax;
import com.example.paymytax.exception.RecordNotFoundException;
import com.example.paymytax.exception.TaxCalculationException;

public interface UAVService {
	
	public List<String> getAllDescriptions() throws RecordNotFoundException;
	
	public int calculateTax(UserTax user) throws TaxCalculationException;

}
