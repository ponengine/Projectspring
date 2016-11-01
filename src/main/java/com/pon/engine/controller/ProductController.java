package com.pon.engine.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.pon.engine.service.*;
import com.pon.engine.domain.Product;
import com.pon.engine.domain.repository.ProductRepository;


@Controller
@RequestMapping("/products")
public class ProductController {
	

	@RequestMapping("/all")
	public ModelAndView allProducts(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	}

	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		System.out.println(productCategory);
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
			Model model) {
		System.out.println("filter:" + filterParams);
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		System.out.println("Model:" + model);
		return "products";
	}
	@RequestMapping("/logout")
	public String logout(){
		return "logout";
	}
	@RequestMapping("/failed")
	public String failed(){
		return "failed";
	}
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId,
	Model model) {
	model.addAttribute("product",productService.getProductById(productId));
	return "product";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
	Product newProduct = new Product();
	
	Random number = new Random();
	int Number = number.nextInt(9999)+1000;
	String strNumber=Integer.toString(Number);
	newProduct.setProductId("P"+strNumber);
	model.addAttribute("newProduct", newProduct);
	System.out.println(newProduct);
	return "addProduct";
	} 
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct")
	Product newProduct, BindingResult result, HttpServletRequest request) {
	
	String[] suppressedFields = result.getSuppressedFields();
	if (suppressedFields.length > 0) {
	throw new RuntimeException("Attempting to bind disallowed fields: "
	+ StringUtils.arrayToCommaDelimitedString(suppressedFields));}
	
	MultipartFile productImage =newProduct.getProductImage();
	String rootDirectory=request.getSession().getServletContext().getRealPath("/");
	if (productImage!=null && !productImage.isEmpty()) {
	try {
	productImage.transferTo(new File(rootDirectory+"resources\\images\\"+newProduct.getProductId() + ".jpg"));
	} catch (Exception e) {
	throw new RuntimeException("Product Image saving failed",e);
	}
	}
	productService.addProduct(newProduct);
	return "forward:/products/all";
	}

	@Autowired
	private ProductService productService;
	@InitBinder
	public void initialiseBinder(WebDataBinder binder){
		binder.setAllowedFields("productId","name","unitPrice","description","manufacturer","category","unitsInStock", "condition","productImage","language", "productImage");
	}
	
}
