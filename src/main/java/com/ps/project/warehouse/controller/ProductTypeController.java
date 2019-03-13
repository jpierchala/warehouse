package com.ps.project.warehouse.controller;

import com.ps.project.warehouse.Repository.ProductTypeRepository;
import com.ps.project.warehouse.domain.ProductType;
import com.ps.project.warehouse.exceptions.ProductTypeNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping(path = "/list")
    public String getProductTypeList(Model model){
        model.addAttribute("productTypeList", productTypeRepository.findAll());
        return "productTypeList";
    }

    @GetMapping(path = "/create")
    public String getCreateProductType(Model model){
        model.addAttribute("productType", new ProductType());
        return "createProductType";
    }

    @PostMapping(path = "/create")
    public String PostCreateProductType(RedirectAttributes redirectAttributes, @Valid @ModelAttribute ProductType productType, BindingResult bindingResult){

        if(!bindingResult.hasErrors() && productTypeRepository.findByName(productType.getName()) == null){
            productTypeRepository.save(productType);
            redirectAttributes.addFlashAttribute("success", "Product type has been created.");
        }else {
            redirectAttributes.addFlashAttribute("error", "ProductType could not be created. Check the name.");
        }

        return "redirect:create";
    }

    @GetMapping("/delete")
    public String deleteProductType(Model model, @RequestParam long productTypeId){
        if(productTypeRepository.existsById(productTypeId)){
            productTypeRepository.deleteById(productTypeId);
            model.addAttribute("success", "productObj has been deleted");
        }else {
            throw new ProductTypeNotExistException();
        }
        return "redirect:productTypesList";
    }
}
