package com.example.paymytax.service.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.paymytax.controller.MainController;
import com.example.paymytax.dao.UAVRepository;
import com.example.paymytax.entity.UserTax;
import com.example.paymytax.exception.RecordNotFoundException;
import com.example.paymytax.exception.TaxCalculationException;
import com.example.paymytax.service.UAVService;

@Service
public class UAVServiceImpl implements UAVService{
	
	private final static Logger logger = LoggerFactory.getLogger(UAVServiceImpl.class);
	
	@Autowired
	UAVRepository uavRepo;

	@Override
	@Transactional
	public List<String> getAllDescriptions() throws RecordNotFoundException {
		try
		{
			List<String> descriptions = uavRepo.findAllDescriptions();
			if(descriptions.size()==0)
			{
			throw new RecordNotFoundException("No Descriptions Data are  Stored in the Database Conact Admin");
			}
			return descriptions;
		}
		catch(RecordNotFoundException ex)
		{
			throw ex;
		}
		catch(Exception e)
		{
			throw new RecordNotFoundException("Failed to Fetch Description",e.getCause());
		}
	}

	@Override
	@Transactional
	public int calculateTax(UserTax user) throws TaxCalculationException {
		
		logger.info("Calculation of tax function is called");
		int depreciationValue=0;
		int unitAreaValue=0;
		try
		{
		depreciationValue = calculateDepreciation(user.getContsructedYear(),user.getYearOfAssessment());
		unitAreaValue= (uavRepo.getUnitAreaValue(user.getStatus().toString(), user.getDescription(), user.getZone().getId()));
		//calculation begins
		}
		catch(Exception ex)
		{
			throw new TaxCalculationException("No Unit Area Value found in The Record for the Zone");
		}
		try
		{
		int total1 = user.getArea()*unitAreaValue*10;
		int total2 = total1-((total1*depreciationValue)/100);
		int total3 = ((total2*20)/100);
		int total4 = ((total3*24)/100);
		return (total3 + total4);
		}
		catch(Exception e)
		{
			throw new TaxCalculationException("Exception occured while calculating the tax");
		}
	}
	
	private int calculateDepreciation(int constructedYear,int assessementYear)
	{
		if(assessementYear-constructedYear>=60)
		return 60;
		else
		return assessementYear-constructedYear;
	}

}
