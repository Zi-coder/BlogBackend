package Spring.Blog.Services;

import Spring.Blog.Model.BlogDetail;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface BlogDetailService {
    List<BlogDetail> blogMetaDetails(Long id);

    void editBlog(Long id, BlogDetail newDetail, Principal principal);

    void createBlog(Long id, BlogDetail blogDetail, Principal principal);

    void deleteComment(Long id);
}
