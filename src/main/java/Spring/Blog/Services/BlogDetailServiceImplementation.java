package Spring.Blog.Services;

import Spring.Blog.DAO.BlogDAO;
import Spring.Blog.DAO.BlogDetailDAO;
import Spring.Blog.Model.BlogDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@Service
public class BlogDetailServiceImplementation implements BlogDetailService {
    @Autowired
    BlogDetailDAO blogDetailDAO;
    @Autowired
    BlogDAO blogDAO;
    @Autowired
    CurrentUserService currentUserService;
    @Override
    public List<BlogDetail> blogMetaDetails(Long id){
       return blogDetailDAO.findAllByBlog( blogDAO.findById(id).get() );
    }
    @Override
    public void editBlog(Long id, BlogDetail newDetail, Principal principal){
        BlogDetail oldDetail = blogDetailDAO.findById(id).get();
        oldDetail.setLiked(newDetail.getLiked());
        oldDetail.setDisliked(newDetail.getDisliked());
        oldDetail.setComment(newDetail.getComment());
        blogDetailDAO.save(oldDetail);

    }
    @Override
    public void createBlog(Long id, BlogDetail blogDetail, Principal principal){
        blogDetail.setReactor(currentUserService.currentUser(principal));
        blogDetail.setBlog(blogDAO.findById(id).get());
        blogDetailDAO.save(blogDetail);
    }
    @Override
    public void deleteComment(Long id){
       blogDetailDAO.delete(blogDetailDAO.findById(id).get());
    }
}
