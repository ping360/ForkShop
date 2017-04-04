package ua.com.forkShop.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

import ua.com.forkShop.dto.filter.FeatureDigitalFilter;
import ua.com.forkShop.dto.filter.ItemFilter;
import ua.com.forkShop.dto.form.ItemForm;
import ua.com.forkShop.editor.BrandEditor;
import ua.com.forkShop.editor.CategoryEditor;
import ua.com.forkShop.editor.DigitalUnitEditor;
import ua.com.forkShop.editor.NameOfFeatureDigitalEditor;
import ua.com.forkShop.editor.NameOfFeatureStringEditor;
import ua.com.forkShop.entity.Brand;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.service.BrandService;
import ua.com.forkShop.service.CategoryService;
import ua.com.forkShop.service.DigitalUnitService;
import ua.com.forkShop.service.FeatureStringService;
import ua.com.forkShop.service.ItemService;
import ua.com.forkShop.service.NameOfFeatureDigitalService;
import ua.com.forkShop.service.NameOfFeatureStringService;
import ua.com.forkShop.validator.ItemValidator;

@Controller
@RequestMapping("/admin/item")
@SessionAttributes(names="item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private DigitalUnitService digitalUnitService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private NameOfFeatureStringService nameOfFeatureStringService;
	
	@Autowired
	private NameOfFeatureDigitalService nameOfFeatureDigitalService;
	
	@Autowired
	private FeatureStringService featureStringService;
	
	@Autowired
	private BrandService brandService;
	
	@InitBinder("item")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(NameOfFeatureStringService.class, new NameOfFeatureStringEditor(nameOfFeatureStringService));
		binder.registerCustomEditor(NameOfFeatureDigitalService.class, new NameOfFeatureDigitalEditor(nameOfFeatureDigitalService));
		binder.registerCustomEditor(DigitalUnit.class, new DigitalUnitEditor(digitalUnitService));
		binder.setValidator(new ItemValidator());
	}
	
	@ModelAttribute("item")
	public ItemForm getForm(){
		return new ItemForm();
	}
	
	@ModelAttribute("filter")
	public ItemFilter getFilter(){
		return new ItemFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter){
		model.addAttribute("page", itemService.findAll(filter, pageable));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("nofss", nameOfFeatureStringService.findAllLoadedFS());
		model.addAttribute("nofds", nameOfFeatureDigitalService.findAllLoadedFD());
		model.addAttribute("digitalUnits", digitalUnitService.findAll());
		return "admin-item";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter){
		itemService.delete(id);
		return "redirect:/admin/item"+getParams(pageable, filter);
	}
	
	@RequestMapping("/add/{id}")
	public String showAdd(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter){
		model.addAttribute("digitalUnit",digitalUnitService.findAll());
		model.addAttribute("page", itemService.findAll(filter, pageable));
		model.addAttribute("category", categoryService.findOne(id));
		model.addAttribute("nofss", nameOfFeatureStringService.findByCategoryId(id));
		model.addAttribute("nofds", nameOfFeatureDigitalService.findByCategoryId(id));
		model.addAttribute("brands", brandService.findAll());
		return "admin-item";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("item") @Valid ItemForm item, BindingResult br, Model model, SessionStatus sessionStatus, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter){
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "admin-item";
		}
		itemService.save(item);
		sessionStatus.setComplete();
		return "redirect:/admin/item"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, ItemFilter filter){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		if(!filter.getSearch().isEmpty()){
			buffer.append("&search=");
			buffer.append(filter.getSearch());
		}
		if(!filter.getMaxPrice().isEmpty()){
			buffer.append("&maxPrice=");
			buffer.append(filter.getMaxPrice());
		}
		if(!filter.getMinPrice().isEmpty()){
			buffer.append("&minPrice=");
			buffer.append(filter.getMinPrice());
		}
		for(Integer id : filter.getBrandIds()){
			buffer.append("&brandIds=");
			buffer.append(id);
		}
		for(Integer id : filter.getFsIds()){
			buffer.append("&fsIds=");
			buffer.append(id);
		}
		int i = 0;
		for(FeatureDigitalFilter digitFilter : filter.getFeatureDigitalFilter()){
			if(digitFilter.getNameId()!=null){
				buffer.append("&featureDigitFilters[");
				buffer.append(i);
				buffer.append("].nameId=");
				buffer.append(digitFilter.getNameId());
			}
			if(!digitFilter.getMin().isEmpty()){
				buffer.append("&featureDigitFilters[");
				buffer.append(i);
				buffer.append("].min=");
				buffer.append(digitFilter.getMin());
			}
			if(!digitFilter.getMax().isEmpty()){
				buffer.append("&featureDigitFilters[");
				buffer.append(i);
				buffer.append("].max=");
				buffer.append(digitFilter.getMax());
			}
			if(digitFilter.getDuId()!=null){
				buffer.append("&featureDigitFilters[");
				buffer.append(i);
				buffer.append("].msId=");
				buffer.append(digitFilter.getDuId());
			}
			i++;
		}
		return buffer.toString();
	}
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/brand";
	}
}