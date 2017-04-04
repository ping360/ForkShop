package ua.com.forkShop.controller.admin;

import static ua.com.forkShop.service.utils.ParamBuilder.getParams;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.editor.CategoryEditor;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.service.CategoryService;
import ua.com.forkShop.service.NameOfFeatureDigitalService;
import ua.com.forkShop.service.NameOfFeatureStringService;
import ua.com.forkShop.validator.CategoryValidator;

@Controller
@RequestMapping("/admin/category")
@SessionAttributes(names = "category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("category")
	public Category getForm() {
		return new Category();
	}

	@ModelAttribute("filter")
	public BasicFilter getFilter() {
		return new BasicFilter();
	}

	@Autowired
	private NameOfFeatureStringService nameOfFeatureStringService;

	@Autowired
	private NameOfFeatureDigitalService nameOfFeatureDigitalService;

	@InitBinder("category")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.setValidator(new CategoryValidator(categoryService));
	}

	@PostMapping
	public String save(@ModelAttribute("category") @Valid Category category, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return show(model);
		}
		categoryService.save(category);
		return "redirect:/admin/category";
	}

	@RequestMapping
	public String show(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "admin-category";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("category", categoryService.findOne(id));
		return show(model);
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		categoryService.delete(id);
		return "redirect:/admin/category";
	}

	@RequestMapping("/add/nofs/{id}")
	public String showAddNofs(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		Category category = categoryService.loadedNofs(id);
		model.addAttribute("category", category);
		model.addAttribute("page", nameOfFeatureStringService.findAllExcludeLoaded(filter, pageable, category));
		return "admin-addNofs";
	}
	
	@RequestMapping("/add/nofs/{id}/{nofsId}")
	public String saveAddNoss(@PathVariable int id, @PathVariable int nofsId, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.addNofs(id, nofsId);
		return "redirect:/admin/category/add/nofs/"+id+getParams(pageable, filter);
	}
	
	@RequestMapping("/delete/nofs/{id}/{nofsId}")
	public String deleteNofs(@PathVariable int id,@PathVariable int nofsId,  @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.deleteNofs(id, nofsId);
		return "redirect:/admin/category/add/nofs/"+id+getParams(pageable, filter);
	}
	
	@RequestMapping("/add/nofd/{id}")
	public String showAddNosd(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		Category category = categoryService.loadedNofd(id);
		model.addAttribute("category", category);
		model.addAttribute("page", nameOfFeatureDigitalService.findAllExcludeLoaded(filter, pageable, category));
		return "admin-addNofd";
	}
	
	@RequestMapping("/add/nofd/{id}/{nofdId}")
	public String saveAddNofd(@PathVariable int id,@PathVariable int nofdId, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.addNofd(id, nofdId);
		return "redirect:/admin/category/add/nofd/"+id+getParams(pageable, filter);
	}
	
	@RequestMapping("/delete/nofd/{id}/{nofdId}")
	public String deleteNofd(@PathVariable int id,@PathVariable int nofdId,  @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.deleteNofd(id, nofdId);
		return "redirect:/admin/category/add/nofd/"+id+getParams(pageable, filter);
}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/category";
	}
}