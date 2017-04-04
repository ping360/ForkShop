package ua.com.forkShop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.forkShop.entity.Category;
import ua.com.forkShop.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport {

	private final CategoryService categoryService;

	public CategoryEditor(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Category category = categoryService.findOne(Integer.valueOf(text));
		setValue(category);
	}
}