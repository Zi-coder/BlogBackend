package Spring.Blog.Controller;


import Spring.Blog.Model.User;
import Spring.Blog.Services.CurrentUserService;
import Spring.Blog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CurrentUserService currentUserService;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        userService.register(user);
        return "\"Registered\"";
    }
    @GetMapping("/auth")
    public String authenticate(){
        return "\"Authenticated\"";
    }

    @GetMapping("/current")
    public User currentUser(Principal principal) throws CloneNotSupportedException {
        return currentUserService.currentUser(principal);
    }
    @PostMapping("/editDetails")
    public String editDetails(@RequestBody User user){
        userService.editUserDetails(user);
        return "\"Details Updated\"";
    }

}
