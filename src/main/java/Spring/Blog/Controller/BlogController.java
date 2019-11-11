package Spring.Blog.Controller;

import Spring.Blog.Model.Blogs;
import Spring.Blog.Services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @PostMapping ("/create")
    public String createBlog(@RequestBody Blogs blog, Principal principal){
       return blogService.saveBlog(blog,principal);
    }
    @GetMapping("/publicBlogs")
    public List<Blogs> fetchPublicBlog(Principal principal){
        return blogService.fetchPublicBlogOthers(principal);
    }
    @GetMapping("/privateBlogs")
    public List<Blogs> fetchPrivateBlogs(Principal principal){
        return blogService.fetchBlogsOfFollowing(principal);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id,Principal principal){
        blogService.deleteBlog(id,principal);
        return "\"Blog Deleted Successfully\"";
    }
    @GetMapping("/details/{id}")
    public Blogs blogDetails(@PathVariable ("id")Long id){
        return blogService.fetchSingleBlog(id);
    }

    @GetMapping("creator/{id}")
    public List<Blogs> fetchPublicBlogsByCreator(@PathVariable("id")Long id){
        return blogService.findPublicBlogsByCreator(id);
    }
    @GetMapping("/current")
    public List<Blogs> blogsByCurrentUser(Principal principal){
        return blogService.findBlogsOfCurrent(principal);
    }

    @GetMapping("/private/{category}")
    public List<Blogs> fetchPrivateByCategory(Principal principal,@PathVariable("category")String category){
        return blogService.getPrivateByCategory(principal,category);
    }
    @GetMapping("/public/{category}")
    public List<Blogs> fetchPublicByCategory(Principal principal,@PathVariable("category")String category){
        return blogService.getPublicBlogsByCategory(principal,category);
    }
}
