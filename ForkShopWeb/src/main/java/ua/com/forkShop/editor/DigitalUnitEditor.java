package ua.com.forkShop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.service.DigitalUnitService;

public class DigitalUnitEditor extends PropertyEditorSupport {

	private final DigitalUnitService digitalUnitService;
	
	public DigitalUnitEditor(DigitalUnitService digitalUnitService){
		this.digitalUnitService = digitalUnitService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		DigitalUnit du = digitalUnitService.findOne(Integer.valueOf(text));
		setValue(du);
}
}