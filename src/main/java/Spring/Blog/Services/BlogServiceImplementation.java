package Spring.Blog.Services;

import Spring.Blog.DAO.BlogDAO;
import Spring.Blog.Model.BlogDetail;
import Spring.Blog.Model.Blogs;
import Spring.Blog.Model.Follow;
import Spring.Blog.Model.User;
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
    @Autowired
    UserService userService;
    @Autowired
    BlogDetailService blogDetailService;
    @Override
    public String saveBlog(Blogs blog, Principal principal){
        blog.setCreator(currentUserService.currentUser(principal));
        blog.setDate(new Date());
        blogDAO.save(blog);
        return "\"Blog Saved\"";

    }
    @Override
    public List<Blogs> fetchPublicBlogOthers(Principal principal){
        return blogDAO.findAllByCreatorNotAndPrEqualsOrderByDate(currentUserService.currentUser(principal),"false");
    }
    @Override
    public List<Blogs> fetchBlogsOfFollowing(Principal principal){
        List<Blogs> ret = new LinkedList<Blogs>();
        List<Follow> follows = followService.getFollowing(principal);
        for(Follow follow: follows){
           List<Blogs> fetched = blogDAO.findAllByCreatorAndPrEqualsOrderByDate(follow.getFollowing(),"true");
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
    @Override
    public List<Blogs> findPublicBlogsByCreator(Long id){
        User user = userService.findUserById(id);
        return   blogDAO.findAllByCreatorAndPrEqualsOrderByDate(user,"false");
    }

    @Override
    public List<Blogs> findBlogsOfCurrent(Principal principal){
     return  blogDAO.findAllByCreator(currentUserService.currentUser(principal));
    }
    @Override
    public void deleteBlog(Long id, Principal principal){
        Blogs victim = blogDAO.findByIdAndCreator(id,currentUserService.currentUser(principal));
        List<BlogDetail> victimDetails = blogDetailService.getBlogDetailByBlog(victim);
        for(BlogDetail blogDetail : victimDetails){
            blogDetailService.deleteComment(blogDetail.getId());
        }
        blogDAO.delete(victim);
    }

    //Category Filter
    @Override
    public List<Blogs> getPrivateByCategory(Principal principal,String Category){
        List<Blogs> reto = new LinkedList<Blogs>();
        List<Follow> follows = followService.getFollowing(principal);
        for(Follow follow: follows){
            List<Blogs> fetched = blogDAO.findAllByCreatorAndPrEqualsAndCategory(follow.getFollowing(),"true",Category);
            for(Blogs blog : fetched){
                reto.add(blog);
            }
        }
        return reto;
    }
    @Override
    public List<Blogs> getPublicBlogsByCategory(Principal principal, String category){
    return  blogDAO.findAllByCreatorNotAndPrEqualsAndCategory(currentUserService.currentUser(principal),"false",category);
    }
}
