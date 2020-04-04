package com.cognizant.truyum.validate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cognizant.truyum.model.MenuItem;

@Component
public class CustomValidator implements Validator {

	
	public void validate(Object obj, Errors error) {
		
		MenuItem item=(MenuItem)obj;
		
		if(item.getDateOfLaunch()==null)
			error.rejectValue("dateOfLaunch","error.dateOfLaunch","Date is required");
	
	}	 	  	    	    	     	      	 	

	public boolean supports(Class<?> arg0) {
		
		
		return MenuItem.class.isAssignableFrom(arg0);
	}
	

}
