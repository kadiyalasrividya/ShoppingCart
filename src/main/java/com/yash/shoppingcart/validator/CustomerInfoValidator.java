package com.yash.shoppingcart.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.yash.shoppingcart.model.CustomerInfo;

@Component
public class CustomerInfoValidator implements Validator {
	
	private EmailValidator emailValidator = EmailValidator.getInstance();
	

	public boolean supports(Class<?> user) {
		return user == CustomerInfo.class;
	}

	public void validate(Object target, Errors error) {
		CustomerInfo customerInfo= (CustomerInfo)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "name","NotEmpty.CustomerForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email","NotEmpty.CustomerForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "address","NotEmpty.CustomerForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "phone","NotEmpty.CustomerForm.phone");
		if(!emailValidator.isValid(customerInfo.getEmail())){
			error.rejectValue("email", "Pattern.CustomerForm.email");
		}	
	}
}
