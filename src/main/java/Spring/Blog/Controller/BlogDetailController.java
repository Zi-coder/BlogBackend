package Spring.Blog.Controller;

import Spring.Blog.Model.BlogDetail;
import Spring.Blog.Services.BlogDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/detail")
public class BlogDetailController {
    @Autowired
    BlogDetailService blogDetailService;

    @GetMapping("/metadata/{id}")
    public List<BlogDetail> getMetaData(@PathVariable("id") Long id){
        return blogDetailService.blogMetaDetails(id);
    }
    @PostMapping("/create/{action}&{id}")
    public String manageDetail(@PathVariable (value = "action")int action, @PathVariable("id") Long id, @RequestBody BlogDetail blogDetail, Principal principal){
        if(action == 0){
            //create new
            blogDetailService.createBlog(id,blogDetail,principal);
            return "\"Blog Posted\"";
        }else{
            //edit blog

            blogDetailService.editBlog(id,blogDetail,principal);
            return "\"Blog Edited\"";
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteComment(@PathVariable("id")Long id){
        blogDetailService.deleteComment(id);
        return "\"Comment Deleted\"";
    }
}
