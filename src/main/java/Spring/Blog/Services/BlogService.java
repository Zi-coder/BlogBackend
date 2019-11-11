package Spring.Blog.Services;

import Spring.Blog.Model.Blogs;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface BlogService {
    String saveBlog(Blogs blog, Principal principal);

    List<Blogs> fetchPublicBlogOthers(Principal principal);

    List<Blogs> fetchBlogsOfFollowing(Principal principal);

    Blogs fetchSingleBlog(Long id);
}
