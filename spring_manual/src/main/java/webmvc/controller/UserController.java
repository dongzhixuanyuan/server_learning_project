package webmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import webmvc.entity.User;
import webmvc.feature.jms.MailMessage;
import webmvc.feature.jms.MessagingService;
import webmvc.service.UserService;

import java.util.HashMap;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService mUserService;

    @Autowired
    MessagingService mMessagingService;

    @RequestMapping("/add")
    public String insertUser(@RequestParam("email")String email, @RequestParam("name")String name, @RequestParam("password")String password) {
        return mUserService.insertUser(email,name,password);
    }

    @RequestMapping("/query")
    public User queryUser(@RequestParam("name") String name) {
        return mUserService.findUser(name);
    }

    @RequestMapping("/sign")
    public ModelAndView signIn(){
        return new ModelAndView("signin.jsp");
    }



    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleUnknownException(Exception ex){
        HashMap<String, Object> map = new HashMap<>();
        map.put("error",ex.getClass().getSimpleName());
        map.put("message",ex.getMessage());
        return new ModelAndView("500.html", map);
    }

    @RequestMapping("/signin")
    public ModelAndView handleChat(){
        return new ModelAndView("chat.jsp");
    }

    @RequestMapping("/register")
    public String regiter(@RequestParam("email")String email,@RequestParam("name")String name) {
        String user = mUserService.insertUser(email, name, "f80ajfa");
        try {
            mMessagingService.sendMailMessage(new MailMessage(email));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }





}
