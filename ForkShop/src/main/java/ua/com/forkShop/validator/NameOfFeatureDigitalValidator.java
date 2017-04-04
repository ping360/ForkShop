package ua.com.forkShop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.forkShop.entity.NameOfFeatureDigital;
import ua.com.forkShop.service.NameOfFeatureDigitalService;

public class NameOfFeatureDigitalValidator implements Validator {

	private NameOfFeatureDigitalService nameOfFeatureDigitalService;

	public NameOfFeatureDigitalValidator(NameOfFeatureDigitalService nameOfFeatureDigitalService) {
		this.nameOfFeatureDigitalService = nameOfFeatureDigitalService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NameOfFeatureDigital.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NameOfFeatureDigital nofd = (NameOfFeatureDigital) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(nameOfFeatureDigitalService.findOne(nofd.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
}
