package webmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webmvc.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService mUserService;

    @RequestMapping("/user")
    public String insertUser(@RequestParam("email")String email, @RequestParam("name")String name, @RequestParam("password")String password) {
        return mUserService.insertUser(email,name,password);
    }
}
