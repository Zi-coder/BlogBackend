package Spring.Blog.DAO;

import Spring.Blog.Model.Blogs;
import Spring.Blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDAO extends JpaRepository<Blogs,Long> {


    List<Blogs> findAllByPrEqualsOrderByDate(String pr);
    List<Blogs> findAllByCreaterNotAndPrEquals(User user,String pr);

}
