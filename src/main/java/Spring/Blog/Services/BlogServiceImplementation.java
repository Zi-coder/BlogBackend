package Spring.Blog.Services;

import Spring.Blog.DAO.BlogDAO;
import Spring.Blog.Model.Blogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImplementation implements BlogService {
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    BlogDAO blogDAO;
    @Override
    public String saveBlog(Blogs blog, Principal principal){
        blog.setCreater(currentUserService.currentUser(principal));
        blog.setDate(new Date());
        blogDAO.save(blog);
        return "\"Blog Saved\"";

    }
    @Override
    public List<Blogs> fetchPublicBlogOthers(Principal principal){
        return blogDAO.findAllByCreaterNotAndPrEquals(currentUserService.currentUser(principal),"false");
    }
}
