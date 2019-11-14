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
    public void editBlog(Principal principal, Long id, Blogs newBlog){
       Blogs oldBlog =  blogDAO.findByIdAndCreator(id,currentUserService.currentUser(principal));
       oldBlog.setDate(new Date());
       oldBlog.setCategory(newBlog.getCategory());
       oldBlog.setContent(newBlog.getContent());
       oldBlog.setTopic(newBlog.getTopic());
       oldBlog.setPr(newBlog.getPr());
       blogDAO.save(oldBlog);
    }

    @Override
    public List<Blogs> fetchPublicBlogOthers(Principal principal){
        return blogDAO.findAllByCreatorNotAndPrEqualsOrderByDateDesc(currentUserService.currentUser(principal),"false");
    }
    @Override
    public List<Blogs> fetchBlogsOfFollowing(Principal principal){
        List<Blogs> ret = new LinkedList<Blogs>();
        List<Follow> follows = followService.getFollowing(principal);
        for(Follow follow: follows){
           List<Blogs> fetched = blogDAO.findAllByCreatorAndPrEqualsOrderByDateDesc(follow.getFollowing(),"true");
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
        return   blogDAO.findAllByCreatorAndPrEqualsOrderByDateDesc(user,"false");
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
            List<Blogs> fetched = blogDAO.findAllByCreatorAndPrEqualsAndCategoryOrderByDateDesc(follow.getFollowing(),"true",Category);
            for(Blogs blog : fetched){
                reto.add(blog);
            }
        }
        return reto;
    }
    @Override
    public List<Blogs> getPublicBlogsByCategory(Principal principal, String category){
    return  blogDAO.findAllByCreatorNotAndPrEqualsAndCategoryOrderByDateDesc(currentUserService.currentUser(principal),"false",category);
    }
    //queryResult
    @Override
    public List<Blogs> getPrivateByQuery(Principal principal, String topic){
        List<Blogs> repo = new LinkedList<Blogs>();
        List<Follow> follows = followService.getFollowing(principal);
        for(Follow follow: follows){
            List<Blogs> fetchedTopic  = blogDAO.findAllByCreatorAndPrEqualsAndTopicContainsOrderByDateDesc(follow.getFollowing(),"true",topic);
            List<Blogs> fetchedContent= blogDAO.findAllByCreatorAndPrEqualsAndContentContainsOrderByDateDesc(follow.getFollowing(),"true",topic);
            for(Blogs blog : fetchedTopic){
                repo.add(blog);
            }
            for(Blogs blog:fetchedContent){
                if(!repo.contains(blog)){
                    repo.add(blog);
                }
            }
        }
        return repo;
    }
    @Override
    public List<Blogs> getPublicByQuery(Principal principal, String query){

        List<Blogs> topicList =  blogDAO.findAllByCreatorNotAndPrEqualsAndTopicContainsOrderByDateDesc(currentUserService.currentUser(principal),"false",query);
        List<Blogs> contentList =blogDAO.findAllByCreatorNotAndPrEqualsAndContentContainsOrderByDateDesc(currentUserService.currentUser(principal),"false",query);
        for(Blogs blog: contentList){
            if(!topicList.contains(blog)){
                topicList.add(blog);
            }
        }
        return  topicList;
    }

}
