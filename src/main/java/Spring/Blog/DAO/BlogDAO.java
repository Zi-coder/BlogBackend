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


//    List<Blogs> findAllByPrEqualsOrderByDate(String pr);
    List<Blogs> findAllByCreatorNotAndPrEqualsOrderByDate(User user,String pr);
    List<Blogs> findAllByCreatorAndPrEqualsOrderByDate(User creator,String pr);
    List<Blogs> findAllByCreator(User user);
    Blogs findByIdAndCreator(Long id,User creator);
    List<Blogs> findAllByCreatorAndPrEqualsAndCategory(User creator,String pr,String category);
    List<Blogs> findAllByCreatorNotAndPrEqualsAndCategory(User currentUser,String pr,String Category);
}
