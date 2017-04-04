package ua.com.forkShop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.forkShop.entity.Brand;
import ua.com.forkShop.service.BrandService;

public class BrandEditor extends PropertyEditorSupport{

	private final BrandService brandService;
	
	public BrandEditor(BrandService brandService){
		this.brandService = brandService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Brand brand = brandService.findOne(Integer.valueOf(text));
		setValue(brand);
	}
	
	
}