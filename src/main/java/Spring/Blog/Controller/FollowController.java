package Spring.Blog.Controller;

import Spring.Blog.Model.Follow;
import Spring.Blog.Services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    FollowService followService;

    @GetMapping("/following")
    public List<Follow> listOfFollowing(Principal principal){
        return followService.getFollowing(principal);
    }

    @GetMapping("/followRequest/{id}")
    public String newFollow(@PathVariable (value = "id")Long id,Principal principal){
       return followService.newFollow(id,principal);
    }
    @DeleteMapping("/unfollow/{id}")
    public String unFollow(@PathVariable (value = "id")Long id,Principal principal){
        return followService.unFollow(id,principal);
    }

}
