package ua.com.forkShop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.forkShop.entity.NameOfFeatureString;
import ua.com.forkShop.service.NameOfFeatureStringService;

public class NameOfFeatureStringValidator implements Validator {

	private NameOfFeatureStringService nameOfFeatureStringService;

	public NameOfFeatureStringValidator(NameOfFeatureStringService nameOfFeatureStringService) {
		this.nameOfFeatureStringService = nameOfFeatureStringService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NameOfFeatureString.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NameOfFeatureString nofs = (NameOfFeatureString) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(nameOfFeatureStringService.findOne(nofs.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
}
