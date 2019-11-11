package Spring.Blog.Services;

import Spring.Blog.DAO.BlogDAO;
import Spring.Blog.Model.Blogs;
import Spring.Blog.Model.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class BlogServiceImplementation implements BlogService {
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    BlogDAO blogDAO;
    @Autowired
    FollowService followService;
    @Override
    public String saveBlog(Blogs blog, Principal principal){
        blog.setCreator(currentUserService.currentUser(principal));
        blog.setDate(new Date());
        blogDAO.save(blog);
        return "\"Blog Saved\"";

    }
    @Override
    public List<Blogs> fetchPublicBlogOthers(Principal principal){
        return blogDAO.findAllByCreatorNotAndPrEquals(currentUserService.currentUser(principal),"false");
    }
    @Override
    public List<Blogs> fetchBlogsOfFollowing(Principal principal){
        List<Blogs> ret = new LinkedList<Blogs>();
        List<Follow> follows = followService.getFollowing(principal);
        for(Follow follow: follows){
           List<Blogs> fetched = blogDAO.findAllByCreatorAndPrEquals(follow.getFollowing(),"true");
           for(Blogs blog : fetched){
               ret.add(blog);
           }
        }
        return ret;
    }
    @Override
    public Blogs fetchSingleBlog(Long id){
        return blogDAO.findById(id).get();
    }
}
