package Spring.Blog.Services;

import Spring.Blog.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User register(User user);

    User userByUsername(String username);

    void editUserDetails(User newDetails);
}
