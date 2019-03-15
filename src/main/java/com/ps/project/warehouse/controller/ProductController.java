package com.ps.project.warehouse.controller;

import com.ps.project.warehouse.Repository.ProductRepository;
import com.ps.project.warehouse.Repository.ProductTypeRepository;
import com.ps.project.warehouse.domain.AddProductsCommand;
import com.ps.project.warehouse.domain.Product;
import com.ps.project.warehouse.domain.Warehouse;
import com.ps.project.warehouse.exceptions.ProductDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/product")
@SessionAttributes({"product"})
@Slf4j
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping(path = "/")
    public String index(){
        return "redirect:list";
    }

    @GetMapping(path = "/list")
    public String getListOfProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "productList";
    }

    @GetMapping(path = "/create")
    public String getCreateProduct(Model model){
        model.addAttribute("productTypes", productTypeRepository.findAll());
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping(path = "/create")
    public String postCreateProduct(Model model, RedirectAttributes redirectAttributes, @Valid @ModelAttribute Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("productTypes", productTypeRepository.findAll());
            model.addAttribute("product", product);
            return "createProduct";
        }else {
            productRepository.save(product);
            redirectAttributes.addFlashAttribute("success", "Product has been created.");
        }
        return "redirect:create";

    }

    @GetMapping(path = "/edit")
    public String getEditProduct(Model model, @RequestParam long productId){
        if(productRepository.existsById(productId)){
            Product product = productRepository.getOne(productId);
            model.addAttribute("product", product);
            model.addAttribute("productTypes", productTypeRepository.findAll());
        }else{
            throw new ProductDoesNotExistException();
        }
        return "editProduct";
    }

    @PostMapping(path = "/edit")
    public String PostEditProduct(Model model, RedirectAttributes redirectAttributes, @Valid @ModelAttribute Product product, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            productRepository.save(product);
            redirectAttributes.addFlashAttribute("success", "Product has been saved");
            return "redirect:list";
        }else{
            model.addAttribute("productId", product.getId());
            model.addAttribute("error", "Product could not be saved. Check form ");
            model.addAttribute("product", productRepository.getOne(product.getId()));
            model.addAttribute("productTypes", productTypeRepository.findAll());
            return "editProduct";
        }
    }

    @GetMapping(path = "/add")
    public String getAddProduct(Model model, @RequestParam long productId){
        if(productRepository.existsById(productId)){
            Product product = productRepository.getOne(productId);
            model.addAttribute("productObj", product);
            return "addProduct";

        }else {
            return "redirect:listProducts";
        }
    }

    @PostMapping(path = "/add")
    public String postAddProduct(@Valid @ModelAttribute AddProductsCommand addProductsCommand,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){

        Product product = productRepository.getOne(addProductsCommand.getProductId());
        Product productFromAnotherWarehouse =  null;
        boolean  success = false;
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", "Something went wrong");
        }else {
            if(addProductsCommand.getSourceProductId() == null) {
                product.setAmount(product.getAmount() + Math.abs(addProductsCommand.getAmount()));
                redirectAttributes.addFlashAttribute("success", "Amount added");
                success = true;
            }else {
                productFromAnotherWarehouse = productRepository.getOne(addProductsCommand.getSourceProductId());
                if (productFromAnotherWarehouse == null){
                    redirectAttributes.addFlashAttribute("error", "Source product deos not exist");
                }else if(addProductsCommand.getAmount() >  productFromAnotherWarehouse.getAmount()){
                    redirectAttributes.addFlashAttribute("error", "Not enaugh amount in warehouse");
                }else {
                    productFromAnotherWarehouse.setAmount(productFromAnotherWarehouse.getAmount() - Math.abs(addProductsCommand.getAmount()));
                    product.setAmount(product.getAmount() + Math.abs(addProductsCommand.getAmount()));
                    redirectAttributes.addFlashAttribute("success", "Amount added");
                    success = true;
                }
            }
            productRepository.save(product);
            if(productFromAnotherWarehouse != null){
                productRepository.save(productFromAnotherWarehouse);
            }
        }
        redirectAttributes.addAttribute("productId", addProductsCommand.getProductId());
        return success ? "redirect:list" : "redirect:add";
    }

    @GetMapping(path = "/delete")
    public String deleteProduct(RedirectAttributes redirectAttributes, @RequestParam Long productId){
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
            redirectAttributes.addFlashAttribute("success", "Product has been deleted");
        }else {
            throw new ProductDoesNotExistException();
        }
        return "redirect:list";
    }

    @GetMapping(path = "/getProduct")
    @ResponseBody
    public Map<String, Object> getProduct(@RequestParam("name") String productName, @RequestParam("source") String source){
        Product product = productRepository.findByNameAndWarehouse(productName, Warehouse.valueOf(source.toUpperCase()));
        Map<String, Object> responseMap = new HashMap<>();
        if(product != null && product.getWarehouse().equals(Warehouse.valueOf(source.toUpperCase()))){
            responseMap.put("success", product);
        }else {
            responseMap.put("error", "No product in other warehouse");
        }
        return responseMap;
    }

}
