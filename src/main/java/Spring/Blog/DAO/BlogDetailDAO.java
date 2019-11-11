package Spring.Blog.DAO;

import Spring.Blog.Model.BlogDetail;
import Spring.Blog.Model.Blogs;
import Spring.Blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDetailDAO extends JpaRepository<BlogDetail,Long> {
    List<BlogDetail> findAllByBlog(Blogs blog);
    BlogDetail findByBlogAndReactor(Blogs blog, User user);
}
