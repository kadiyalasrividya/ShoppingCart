package com.yash.shoppingcart.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.yash.shoppingcart.dao.ProductDao;
import com.yash.shoppingcart.entity.Product;
import com.yash.shoppingcart.model.ProductInfo;

@Component
public class productInfoValidator implements Validator {

	@Autowired
	private ProductDao productDao;
	
	public boolean supports(Class<?> product) {
		return product == ProductInfo.class;
	}

	public void validate(Object target, Errors error) {
		ProductInfo productInfo =(ProductInfo)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "code", "NotEmpty.ProductForm.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "name", "NotEmpty.ProductForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "price", "NotEmpty.ProductForm.price");
		String code = productInfo.getCode();
        if (code != null && code.length() > 0) {
            if (code.matches("\\s+")) {
                error.rejectValue("code", "Pattern.productForm.code");
            } else if(productInfo.isNewProduct()) {
                Product product = productDao.findProduct(code);
                if (product != null) {
                    error.rejectValue("code", "Duplicate.productForm.code");
                }
            }
        }		
	}
}
