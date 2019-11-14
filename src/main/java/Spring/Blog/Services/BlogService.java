package Spring.Blog.Services;

import Spring.Blog.Model.Blogs;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface BlogService {
    String saveBlog(Blogs blog, Principal principal);

    void editBlog(Principal principal, Long id, Blogs newBlog);

    List<Blogs> fetchPublicBlogOthers(Principal principal);

    List<Blogs> fetchBlogsOfFollowing(Principal principal);

    Blogs fetchSingleBlog(Long id);

    List<Blogs> findPublicBlogsByCreator(Long id);

    List<Blogs> findBlogsOfCurrent(Principal principal);

    void deleteBlog(Long id,Principal principal);

    //Category Filter
    List<Blogs> getPrivateByCategory(Principal principal, String Category);

    List<Blogs> getPublicBlogsByCategory(Principal principal, String category);

    //queryResult
    List<Blogs> getPrivateByQuery(Principal principal, String topic);

    List<Blogs> getPublicByQuery(Principal principal, String query);
}
