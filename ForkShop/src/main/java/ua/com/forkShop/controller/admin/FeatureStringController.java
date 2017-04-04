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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.editor.NameOfFeatureStringEditor;
import ua.com.forkShop.entity.FeatureString;
import ua.com.forkShop.entity.NameOfFeatureString;
import ua.com.forkShop.service.FeatureStringService;
import ua.com.forkShop.service.NameOfFeatureStringService;
import ua.com.forkShop.validator.FeatureStringValidator;

@Controller
@RequestMapping("/admin/fs")
@SessionAttributes(names = "fs")
public class FeatureStringController {

	@Autowired
	private FeatureStringService featureStringService;

	@Autowired
	private NameOfFeatureStringService nameOfFeatureStringService;

	@InitBinder("fs")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(NameOfFeatureString.class,
				new NameOfFeatureStringEditor(nameOfFeatureStringService));
		binder.setValidator(new FeatureStringValidator(featureStringService));
	}

	@ModelAttribute("fs")
	public FeatureString getForm() {
		return new FeatureString();
	}

	@ModelAttribute("filter")
	public BasicFilter getFilter() {
		return new BasicFilter();
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("page", featureStringService.findAll(filter, pageable));
		model.addAttribute("nofss", nameOfFeatureStringService.findAll());
		return "admin-featureString";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("fs", featureStringService.findOne(id));
		show(model, pageable, filter);
		return "admin-featureString";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		featureStringService.delete(id);
		return "redirect:/admin/fs" + getParams(pageable, filter);
	}

	@RequestMapping(method = POST)
	public String save(@ModelAttribute("fs") @Valid FeatureString form, BindingResult br, Model model,
			SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		if (br.hasErrors()) {
			show(model, pageable, filter);
			return "admin-featureString";
		}
		featureStringService.save(form);
		status.setComplete();
		return "redirect:/admin/fs" + getParams(pageable, filter);
	}

	@RequestMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/fs";
	}
}
