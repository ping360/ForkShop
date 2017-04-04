package ua.com.forkShop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.forkShop.entity.NameOfFeatureString;
import ua.com.forkShop.service.NameOfFeatureStringService;

public class NameOfFeatureStringEditor extends PropertyEditorSupport{

	private final NameOfFeatureStringService nameOfFeatureStringService;

	public NameOfFeatureStringEditor(NameOfFeatureStringService nameOfFeatureStringService) {
		this.nameOfFeatureStringService = nameOfFeatureStringService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		NameOfFeatureString nofs = nameOfFeatureStringService.findOne(Integer.valueOf(text));
		setValue(nofs);
}
}