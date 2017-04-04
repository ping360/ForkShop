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
import ua.com.forkShop.entity.Brand;
import ua.com.forkShop.service.BrandService;
import ua.com.forkShop.validator.BrandValidator;

@Controller
@RequestMapping("/admin/brand")
@SessionAttributes("brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@ModelAttribute("brand")
	public Brand getForm() {
		return new Brand();
	}

	@ModelAttribute("filter")
	public BasicFilter getFilter() {
		return new BasicFilter();
	}

	@InitBinder("brand")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new BrandValidator(brandService));
	}

	@PostMapping
	public String save(@ModelAttribute("brand")@Valid Brand form, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "admin-brand";
		}
		brandService.save(form);
		status.setComplete();
		return "redirect:/admin/brand"+getParams(pageable, filter);
}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("page", brandService.findAll(filter, pageable));
		return "admin-brand";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("brand", brandService.findOne(id));
		show(model, pageable, filter);
		return "admin-brand";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		brandService.delete(id);
		return "redirect:/admin/brand"+getParams(pageable, filter);
	}

	@RequestMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/brand";
	}
}
