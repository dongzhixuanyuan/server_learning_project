package webmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import webmvc.entity.User;
import webmvc.service.UserService;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService mUserService;

    @RequestMapping("/add")
    public String insertUser(@RequestParam("email")String email, @RequestParam("name")String name, @RequestParam("password")String password) {
        return mUserService.insertUser(email,name,password);
    }

    @RequestMapping("/query")
    public User queryUser(@RequestParam("name") String name) {
        return mUserService.findUser(name);
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleUnknownException(Exception ex){
        HashMap<String, Object> map = new HashMap<>();
        map.put("error",ex.getClass().getSimpleName());
        map.put("message",ex.getMessage());
        return new ModelAndView("500.html", map);
    }

}
