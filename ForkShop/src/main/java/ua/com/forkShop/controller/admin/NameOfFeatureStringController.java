package ua.com.forkShop.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.NameOfFeatureString;
import ua.com.forkShop.service.CategoryService;
import ua.com.forkShop.service.NameOfFeatureStringService;
import ua.com.forkShop.validator.NameOfFeatureStringValidator;

@Controller
@RequestMapping("/admin/nofs")
@SessionAttributes(names = "nofs")
public class NameOfFeatureStringController {

	@Autowired
	private NameOfFeatureStringService nameOfFeatureStringService;

	@Autowired
	private CategoryService categoryService;

	@InitBinder("nofs")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new NameOfFeatureStringValidator(nameOfFeatureStringService));
	}

	@ModelAttribute("nofs")
	public NameOfFeatureString getForm() {
		return new NameOfFeatureString();
	}

	@ModelAttribute("filter")
	public BasicFilter getFilter() {
		return new BasicFilter();
	}

	@RequestMapping(method=POST)
	public String save(@ModelAttribute("nofs")@Valid NameOfFeatureString form, BindingResult br, SessionStatus status, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "admin-nameOfFeatureString";
		}
		nameOfFeatureStringService.save(form);
		status.setComplete();
		return "redirect:/admin/nofs"+getParams(pageable, filter);
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("page", nameOfFeatureStringService.findAll(filter, pageable));
		return "admin-nameOfFeatureString";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("nofs", nameOfFeatureStringService.findOne(id));
		show(model, pageable, filter);
		return "admin-nameOfFeatureString";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		nameOfFeatureStringService.delete(id);
		return "redirect:/admin/nofs"+getParams(pageable, filter);
	}

	@RequestMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/nofs";
	}
}
