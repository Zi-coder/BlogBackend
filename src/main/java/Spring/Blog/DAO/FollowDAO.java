package Spring.Blog.DAO;

import Spring.Blog.Model.Follow;
import Spring.Blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowDAO extends JpaRepository<Follow,Long> {
   List<Follow> findAllByUser(User user);
   Follow findByUserAndFollowing(User user,User following);
}
