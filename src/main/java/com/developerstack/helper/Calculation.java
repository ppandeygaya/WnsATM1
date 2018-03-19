package com.developerstack.helper;

import javax.servlet.http.HttpSession;

public class Calculation {

	public static int[] withdraw(int amount,HttpSession session){
		//amount=100;
		int q50=0;
		int q20=0;
		int r=0;
		int outofCashflag=0;
		int currency[]=new int[2];
		int dllr20=Integer.parseInt( (session.getAttribute("refill$20")).toString());
		int dllr50=Integer.parseInt(session.getAttribute("refill$50").toString());
		int actualAmount=Integer.parseInt(session.getAttribute("refillMoney").toString());
		if(amount%20==0 && amount%50==0){
			if(dllr20<6){
				q50=amount/50;
				if(dllr50>=q50){
					currency[1]=q50;
					
					session.setAttribute("refill$50", (dllr50-q50));
					session.setAttribute("refillMoney", (actualAmount-amount));
					
					}
				else{
					outofCashflag=1;
				}
			}else if(dllr20>dllr50){
				q20=amount/20;
				if(dllr20>=q20){
				currency[0]=q20;
				
				session.setAttribute("refill$20", (dllr20-q20));
				session.setAttribute("refillMoney", (actualAmount-amount));
				}else{
					outofCashflag=1;
				}
			}else{
				q50=amount/50;
				if(dllr50>=q50){
					currency[1]=q50;
					
					session.setAttribute("refill$50", (dllr50-q50));
					session.setAttribute("refillMoney", (actualAmount-amount));
					}else{
						outofCashflag=1;
					}
			}
		
		}else if(amount%20==0){
			
			q20=amount/20;
			if(dllr20>=q20){
			currency[0]=q20;
			
			session.setAttribute("refill$20", (dllr20-q20));
			session.setAttribute("refillMoney", (actualAmount-amount));
			}else{
				outofCashflag=1;
			}
			
		}else if(amount%50==0){
			
			q50=amount/50;
			if(dllr50>=q50){
				currency[1]=q50;
				
				session.setAttribute("refill$50", (dllr50-q50));
				session.setAttribute("refillMoney", (actualAmount-amount));
				}else{
					outofCashflag=1;
				}
			
			
		}else if(amount%50!=0){
			r=amount%50;
			  if(r%20==0){
				q50=amount/50;
				q20=r/20;
			  }
			  if(dllr50>=q50 && dllr20>=q20 ){
				  currency[0]=q20;
				  currency[1]=q50;
					
					session.setAttribute("refill$50", (dllr50-q50));
					session.setAttribute("refill$20", (dllr20-q20));
					session.setAttribute("refillMoney", (actualAmount-amount));
					}
			  else{
				  outofCashflag=1;
				}
			
		}
	session.setAttribute("outofCashflag", outofCashflag);
	
	return currency;
	}
	
}
