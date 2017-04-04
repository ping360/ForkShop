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
import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.service.DigitalUnitService;
import ua.com.forkShop.validator.DigitalUnitValidator;

@Controller
@RequestMapping("/admin/du")
@SessionAttributes(names = "digitalUnit")
public class DigitalUnitController {

	@Autowired
	private DigitalUnitService digitalUnitService;

	@InitBinder("du")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new DigitalUnitValidator(digitalUnitService));
	}

	@ModelAttribute("du")
	public DigitalUnit getForm() {
		return new DigitalUnit();
	}

	@ModelAttribute("filter")
	public BasicFilter getFilter() {
		return new BasicFilter();
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("page", digitalUnitService.findAll(filter, pageable));
		return "admin-digitalUnit";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		digitalUnitService.delete(id);
		return "redirect:/admin/du" + getParams(pageable, filter);
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		model.addAttribute("du", digitalUnitService.findOne(id));
		show(model, pageable, filter);
		return "admin-digitalUnit";
	}

	@RequestMapping(method = POST)
	public String save(@ModelAttribute("du") @Valid DigitalUnit form, BindingResult br, Model model,
			SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
		if (br.hasErrors()) {
			show(model, pageable, filter);
			return "admin-digitalUnit";
		}
		digitalUnitService.save(form);
		status.setComplete();
		return "redirect:/admin/du" + getParams(pageable, filter);
	}

	@RequestMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/du";
	}
}
