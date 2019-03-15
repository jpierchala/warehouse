package com.ps.project.warehouse.exceptions;


import com.ps.project.warehouse.Repository.ProductRepository;
import com.ps.project.warehouse.Repository.ProductTypeRepository;
import com.ps.project.warehouse.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionsHandlers {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    UserRepository userRepository;

    @ExceptionHandler(ProductDoesNotExistException.class)
    public ModelAndView handleProductDoesNotExist(HttpServletRequest request){

        log.info("Product with id: " + request.getParameter("productId").toString() + " not found");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productRepository.findAll());
        modelAndView.addObject("error", "The productObj with that id does not exist");
        modelAndView.setViewName("productList");
        return modelAndView;
    }

    @ExceptionHandler(ProductTypeNotExistException.class)
    public ModelAndView handleProductTypeNotExist(HttpServletRequest request){

        log.info("ProductType with id: " + request.getParameter("productId").toString() + " not found");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productTypes", productTypeRepository.findAll());
        modelAndView.addObject("error", "The productType with that id does not exist");
        modelAndView.setViewName("productTypesList");
        return modelAndView;
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ModelAndView handleUserDoesNotExistException(HttpServletRequest request){

        log.info("User with id: " + request.getParameter("userId").toString() + " not found");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userRepository.findAll());
        modelAndView.addObject("error", "The user with that id does not exist");
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

}
