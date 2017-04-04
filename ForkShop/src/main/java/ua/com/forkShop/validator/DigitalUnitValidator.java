package ua.com.forkShop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.service.DigitalUnitService;

public class DigitalUnitValidator implements Validator {

	private final DigitalUnitService digitalUnitService;
	
	public DigitalUnitValidator(DigitalUnitService digitalUnitService) {
		this.digitalUnitService = digitalUnitService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(DigitalUnit.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DigitalUnit du = (DigitalUnit) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(digitalUnitService.findOne(du.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}

}
