package ua.com.forkShop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.forkShop.entity.Brand;
import ua.com.forkShop.service.BrandService;

public class BrandValidator implements Validator{

	private final BrandService brandService;

	public BrandValidator(BrandService brandService) {
		this.brandService = brandService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Brand.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Brand brand = (Brand) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if (brandService.findOne(brand.getName())!=null) {
			errors.rejectValue("name", "", "Already exist");
		}
	}
	
}
 