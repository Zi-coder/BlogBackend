package Spring.Blog.DAO;

import Spring.Blog.Model.Blogs;
import Spring.Blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDAO extends JpaRepository<Blogs,Long> {


//    List<Blogs> findAllByPrEqualsOrderByDate(String pr);
    List<Blogs> findAllByCreatorNotAndPrEqualsOrderByDateDesc(User user, String pr);
    List<Blogs> findAllByCreatorAndPrEqualsOrderByDateDesc(User creator, String pr);
    List<Blogs> findAllByCreatorOrderByDateDesc(User user);
    Blogs findByIdAndCreator(Long id,User creator);
    List<Blogs> findAllByCreatorAndPrEqualsAndCategoryOrderByDateDesc(User creator, String pr, String category);
    List<Blogs> findAllByCreatorNotAndPrEqualsAndCategoryOrderByDateDesc(User currentUser, String pr, String Category);

    //fetchPrivateOnSearch
    //content
    List<Blogs> findAllByCreatorAndPrEqualsAndContentContainsOrderByDateDesc(User creator, String pr, String content);
    //topic
    List<Blogs> findAllByCreatorAndPrEqualsAndTopicContainsOrderByDateDesc(User creator, String pr, String topic);
    //fetch public Search
    //content
    List<Blogs> findAllByCreatorNotAndPrEqualsAndContentContainsOrderByDateDesc(User currentUser, String pr, String content);
    //topic
    List<Blogs> findAllByCreatorNotAndPrEqualsAndTopicContainsOrderByDateDesc(User creator, String pr, String topic);
}
