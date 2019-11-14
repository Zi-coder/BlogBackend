package Spring.Blog.DAO;

import Spring.Blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {
     User findByUsername(String username);
     Optional<User> findById(Long id);
     List<User> findAllByFullnameContainingOrderByFullname(String fullname);

}
