package com.developerstack.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.developerstack.exception.OutOfCashException;
import com.developerstack.helper.Calculation;
import com.developerstack.model.Refill;
@Controller
public class AtmMailController {
	
	  @RequestMapping(value = "/atm", method = RequestMethod.GET)
	    public String login() {
	    	return "atmwelcome";
	    }
	  
	  @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	  @ExceptionHandler(OutOfCashException.class)
	  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	    public ModelAndView yes(@RequestParam("withdraw") int withdrawMoney,HttpSession session) throws Exception {
	    	
	    	int money=0;
	    	if(session.getAttribute("refillMoney")!=null){
	    	   money=Integer.parseInt(session.getAttribute("refillMoney").toString());	
	    	}
	    	
	    	ModelAndView model=new ModelAndView();
	    	if(session.getAttribute("refillMoney")!=null && money>=withdrawMoney){
        		int currency[]=Calculation.withdraw(withdrawMoney,session);
	    		model.addObject("dr20", currency[0]);
	    		model.addObject("dr50", currency[1]);
	    		int outofCashflag=Integer.parseInt(session.getAttribute("outofCashflag").toString());
	    		if(outofCashflag==1){
	    			model.addObject("error", "Running out of cash");
	    			model.setViewName("atmwelcome");
	    		}else{
	    		model.setViewName("sucess");
	    		}
	    	}else{
	    		
	    		
	    		model.addObject("error", "Running out of cash");
	        	model.setViewName("atmwelcome");
	        	return model;
	    	}
	    	
	    	return model;
	    }
	  
	  
	   
}
