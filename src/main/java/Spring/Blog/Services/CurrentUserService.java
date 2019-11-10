package Spring.Blog.Services;

import Spring.Blog.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
@Component
public class CurrentUserService {
    @Autowired
    UserService userService;
    public User currentUser(Principal principal){
        return  userService.userByUsername(principal.getName());
    }

}
