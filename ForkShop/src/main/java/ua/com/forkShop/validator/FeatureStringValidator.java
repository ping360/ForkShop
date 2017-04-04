package ua.com.forkShop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.forkShop.entity.FeatureString;
import ua.com.forkShop.service.FeatureStringService;

public class FeatureStringValidator implements Validator {

	private final FeatureStringService stringService;

	public FeatureStringValidator(FeatureStringService stringService) {
		this.stringService = stringService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FeatureString.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FeatureString featureString = (FeatureString) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(stringService.findOne(featureString.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
}
}
