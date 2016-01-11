package com.hongzhou.teahouse.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hongzhou.teahouse.domain.Product;
import com.hongzhou.teahouse.exception.NoProductsFoundUnderCategoryException;
import com.hongzhou.teahouse.exception.ProductNotFoundException;
import com.hongzhou.teahouse.service.ProductService;
import com.hongzhou.teahouse.validator.ProductValidator;
//import com.hongzhou.teahouse.validator.UnitsInStockValidator;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//@Autowired
	//private UnitsInStockValidator unitsInStockValidator;
	
	@Autowired
	private ProductValidator productValidator;
	
	@RequestMapping
	public String list(Model model){
		
		model.addAttribute("products", productService.getAllProducts());
		
		return "products";		
	}
	
	@RequestMapping("/all")
	public ModelAndView allProducts(){

		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	}
	
	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory){
		List<Product> products = productService.getProductsByCategory(productCategory);
		
		if(products == null || products.isEmpty()){
			throw new NoProductsFoundUnderCategoryException();
		}
		
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		
		return "products";
	}
	
	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String, List<String>> filterParams, Model model){
		
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		
		return "products";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model){
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}
	
	/*@RequestMapping("/{manufacturer}")
	public String getProductsByManufacturer(Model model, @PathVariable("manufacturer") String manufacturer){
		
		model.addAttribute("products", productService.getProductsByManufacturer(manufacturer));
		
		return "products";
	}*/
	
	/*@RequestMapping("/filter/{ByManufacturer}/{ByCategory}")
	public String filterProducts(@MatrixVariable(pathVar="ByManufacturer") Map<String, List<String>> manufacturerFilter, @MatrixVarialbe (pathVar="ByCategory") Map<String, List<string>> categoryFilter, Model model){
		
		model.addAttribute("products", productService.filterProducts(??));
		
		return "product";
	}*/

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddNewProductForm(@Valid @ModelAttribute("newProduct") Product newProduct, 
			BindingResult result, HttpServletRequest request){
		if(result.hasErrors()){
			return "addProduct";
		}
		
		String[] suppressedFields = result.getSuppressedFields();
		if(suppressedFields.length > 0){
			throw new RuntimeException("Attempting to bind disallowed fields: " 
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if(productImage != null && !productImage.isEmpty()){
			try {
				productImage.transferTo(new File(rootDirectory + "resources\\images\\" +
						newProduct.getProductId() + ".jpg"));
			} catch (Exception e){
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		
		productService.addProduct(newProduct);
		return "redirect:/products";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		//binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacture",
				//"category", "unitsInStock", "productImage", "language");
		binder.setValidator(productValidator);
		binder.setDisallowedFields("unitsInOrder", "discontinued");
		
		//binder.setValidator(unitsInStockValidator);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerError(HttpServletRequest req, ProductNotFoundException exception){
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("productNotFound");
		
		return mav;
	}
	
	@RequestMapping("/invalidPromoCode")
	public String invalidPromoCode(){
		return "invalidPromoCode";
	}
}
