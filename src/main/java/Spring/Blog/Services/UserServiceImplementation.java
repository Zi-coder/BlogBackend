package Spring.Blog.Services;

import Spring.Blog.DAO.UserDAO;
import Spring.Blog.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserDAO userDAO;
    @Override
    public User register(User user){
        user.setActive(1);
        user.setRole("user");
        return userDAO.save(user);
    }
    @Override
    public User userByUsername(String username){
        return userDAO.findByUsername(username);
    }

    @Override
    public void editUserDetails(User newDetails){
       User oldDetails = userDAO.getOne(newDetails.getId());
       oldDetails.setPassword(newDetails.getPassword());
       oldDetails.setBio(newDetails.getBio());
       oldDetails.setContact(newDetails.getContact());
       oldDetails.setFullname(newDetails.getFullname());
       oldDetails.setPhoto(newDetails.getPhoto());
       oldDetails.setUsername(newDetails.getUsername());
       userDAO.save(oldDetails);

    }
}
