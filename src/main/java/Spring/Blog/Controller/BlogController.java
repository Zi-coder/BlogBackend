package Spring.Blog.Controller;

import Spring.Blog.Model.Blogs;
import Spring.Blog.Services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

}
