package com.developerstack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;

import com.developerstack.helper.Calculation;

public class CalculationTest {
     HttpServletRequest request;
	HttpSession session;
	public int withdrawMoney=40;
	public int refillMoney=0;
	public int refill$20=0;
	public int refill$50=0;

	  @Before
	    public void setUp() {
		  MockitoAnnotations.initMocks(this);
		  session = new MockHttpSession();		  
		  session.setAttribute("refill$20", refill$20);
	      	session.setAttribute("refill$50", refill$50);
	      	session.setAttribute("refillMoney", refillMoney);
	    }
	  
	@Test
	public void testWithdraw() {
		Calculation c=new Calculation();
		//HttpSession session = mock(HttpSession.class);
		int currency[]=c.withdraw(withdrawMoney,session);
		int outofCashflag=Integer.parseInt(session.getAttribute("outofCashflag").toString());
		System.out.println("20 Currency is:"+currency[0]);
		System.out.println("50 Currency is:"+currency[1]);
		
	}
	@Test
	public void testOfOutCash() {
		Calculation c=new Calculation();
		//HttpSession session = mock(HttpSession.class);
		int currency[]=c.withdraw(withdrawMoney,session);
		int outofCashflag=Integer.parseInt(session.getAttribute("outofCashflag").toString());
		assertEquals(1, outofCashflag);
		assertEquals(0, outofCashflag);
}
}
