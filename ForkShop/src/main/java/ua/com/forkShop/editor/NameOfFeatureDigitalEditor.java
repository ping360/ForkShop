package ua.com.forkShop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.forkShop.entity.NameOfFeatureDigital;
import ua.com.forkShop.service.NameOfFeatureDigitalService;

public class NameOfFeatureDigitalEditor  extends PropertyEditorSupport{

	private final NameOfFeatureDigitalService digitalService;

	public NameOfFeatureDigitalEditor(NameOfFeatureDigitalService digitalService) {
		this.digitalService = digitalService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		NameOfFeatureDigital nofd = digitalService.findOne(Integer.valueOf(text));
		setValue(nofd);
}
}
