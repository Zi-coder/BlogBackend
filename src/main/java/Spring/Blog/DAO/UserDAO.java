package Spring.Blog.DAO;

import Spring.Blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {
public User findByUsername(String username);
}
