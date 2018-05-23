package com.kong.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * File Name ErrorController
 * Author    lantoev & kong xiang
 * DATE      2018-05-21
 * EMAIL     playboxjre@Gmail.com
 */
@RestController
@RequestMapping("/error")
public class ErrorController {

    @GetMapping()
    public ModelAndView error(Exception ex){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("base/error");
        modelAndView.addObject(ex);
        return modelAndView;
    }
}
