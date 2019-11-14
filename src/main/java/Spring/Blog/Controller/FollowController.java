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
    @GetMapping("/followers")
    public List<Follow> getFollowers(Principal principal){
        return followService.getFollowers(principal);
    }
    @GetMapping("/count/{id}")
    public Long getCountOfFollower(@PathVariable("id")Long id){
        return followService.getFollowerCount(id);
    }
    @GetMapping("/countFollowing/{id}")
    public Long getFollowingCount(@PathVariable("id")Long id){
        return followService.getFollowingCount(id);
    }
    @DeleteMapping("removeFollower/{id}")
    public String removeFollower(@PathVariable("id")Long id,Principal principal){
         followService.deleteFollower(id,principal);
         return "\"Follower Removed\"";
    }
}
