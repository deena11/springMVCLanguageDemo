package com.example.paymytax.exception.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.example.paymytax.exception.RecordNotAddedException;
import com.example.paymytax.exception.RecordNotFoundException;
import com.example.paymytax.exception.TaxCalculationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(TaxCalculationException.class)
	public ResponseEntity<?> handleTaxCalculationException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		Map<String , String> map = new HashMap<String, String>();
		map.put("errorMessage", ex.getMessage());
		map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		map.put("statusMessage", "Internal Server Error Please Retry or contact the ADMIN");
	    return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ModelAndView handleRecordNotFoundException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView map = new ModelAndView();
		map.setViewName("errorPage");
		map.addObject("errorMessage", ex.getMessage());
		map.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		map.addObject("statusMessage", "Internal Server Error Please Retry or contact the ADMIN");
	    return map;
	}
	
	@ExceptionHandler(RecordNotAddedException.class)
	public ModelAndView handleRecordNotAddedException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView map = new ModelAndView();
		map.setViewName("errorPage");
		map.addObject("errorMessage", ex.getMessage());
		map.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		map.addObject("statusMessage", "Internal Server Error Please Retry or contact the ADMIN");
	    return map;
	}

}
