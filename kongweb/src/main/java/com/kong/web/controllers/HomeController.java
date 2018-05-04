package com.kong.web.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.net.URL;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        String path = getClass().getResource("/").getPath();
        model.addAttribute("classpath",path);

        ClassPathResource resource1 = new ClassPathResource("/");
        String path3 = resource1.getPath();

        model.addAttribute("path3",path3);

        String path1 = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        model.addAttribute("classpath1",path1);
        String  path2 = new File(".").getPath();
        model.addAttribute("classpath2",path2);

        URL resource = getClass().getResource("/");
        File file = new File(resource.getFile());
        if (file.isDirectory()){
            File[] files = file.listFiles();

        model.addAttribute("files",files);
        }
        return "home";
    }



}
