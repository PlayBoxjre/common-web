package com.kong.web.controllers;

import com.kong.web.model.Account;
import com.kong.web.supports.model.RestResultSet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public @ResponseBody RestResultSet<Account> login(
          @Valid @RequestBody Account account){

            RestResultSet<Account> restResultSet = new RestResultSet<>();
            restResultSet.setCode(HttpStatus.OK.toString());
            restResultSet.setDatas(account);
            restResultSet.setMessage(HttpStatus.OK.getReasonPhrase());
            return restResultSet;
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



    @GetMapping("/regiester")
    public String form(){
        throw new IllegalArgumentException("hhhh");
    }

    @PostMapping("/register")
    public String form(@Validated Account account, BindingResult errorResult, Model model){
        if (errorResult.hasErrors()){
            return "base/error";
        }

        return "";
    }

}
