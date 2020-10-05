package com.example.paymytax.service;

import java.util.Map;

import com.example.paymytax.entity.UserTax;
import com.example.paymytax.exception.RecordNotAddedException;
import com.example.paymytax.exception.RecordNotFoundException;

public interface UserTaxService {
	
	public String addUserTax(UserTax user) throws RecordNotAddedException;
	
	public Map<String,Map<String,Double>> fetchAllUserTaxDetails(int year) throws RecordNotFoundException;
	
	

}
