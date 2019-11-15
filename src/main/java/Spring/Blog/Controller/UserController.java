package Spring.Blog.Controller;


import Spring.Blog.Model.User;
import Spring.Blog.Services.CurrentUserService;
import Spring.Blog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
class TempData{
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

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
    @GetMapping("/gen-bio/{id}")
    public User getGenBio(@PathVariable("id") Long id){
        User userClone = userService.findUserById(id);
        User retUser = new User();
        retUser.setFullname(userClone.getFullname());
        retUser.setPhoto(userClone.getPhoto());
        retUser.setContact(userClone.getContact());
        retUser.setBio(userClone.getBio());
        return  retUser;
    }
    @GetMapping("/search/q={query}")
    public List<User> getUserByQuery(@PathVariable("query") String fullname){
        return userService.getByQuery(fullname);
    }
    @PostMapping("/change")
    public String changePassword(@RequestBody TempData tempData){
        userService.changePassword(tempData.getUsername(),tempData.getPassword());
        return "\"Password Changed\"";
    }

}
