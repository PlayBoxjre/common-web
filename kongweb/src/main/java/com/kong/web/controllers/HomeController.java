package com.kong.web.controllers;

import com.kong.web.model.Account;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.net.URL;
import java.util.Date;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/home")
    public String home(Model model){
        String path = getClass().getResource("/").getPath();
        model.addAttribute("classpath",path);

        ClassPathResource resource1 = new ClassPathResource("/");
        String path3 = resource1.getPath();

        model.addAttribute("path3",path3);

        String path1 = "";//Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        model.addAttribute("classpath1",path1);
        String  path2 = new File(".").getPath();
        model.addAttribute("classpath2",path2);

        URL resource = getClass().getResource("/");
        File file = new File(resource.getFile());
        if (file.isDirectory()){
            File[] files = file.listFiles();

        model.addAttribute("files",files);
        }
        model.addAttribute("time",new Date());


        return "home";
    }


    @PostMapping("/login")

    public @ResponseBody String login(
            @RequestParam String name,
            @RequestParam  String age,
             @RequestParam  String gender){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","0");
        jsonObject.put("name",name);
        jsonObject.put("age",age);
        jsonObject.put("gender",gender);
        return jsonObject.toString();
    }


    @GetMapping("userinfo")
    public ModelAndView info (Account account){
        ModelAndView modelAndView = new ModelAndView("user");
        account.setName("效力");
        account.setAge(100);
        account.setBirthDay(new Date());

        modelAndView.addObject(account);
        return modelAndView;
    }


}
