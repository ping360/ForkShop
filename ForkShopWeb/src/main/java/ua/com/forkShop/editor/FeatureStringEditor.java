package ua.com.forkShop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.forkShop.entity.FeatureString;
import ua.com.forkShop.service.FeatureStringService;

public class FeatureStringEditor extends PropertyEditorSupport {

	private final FeatureStringService service;

	public FeatureStringEditor(FeatureStringService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		FeatureString fs = service.findOne(Integer.valueOf(text));
		setValue(fs);
}
}
