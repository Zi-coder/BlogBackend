package Spring.Blog.Services;

import Spring.Blog.DAO.UserDAO;
import Spring.Blog.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User findUserById(Long id){
        return userDAO.findById(id).get();
    }
    @Override
    public void editUserDetails(User newDetails){
       User oldDetails = userDAO.getOne(newDetails.getId());
       oldDetails.setBio(newDetails.getBio());
       oldDetails.setContact(newDetails.getContact());
       oldDetails.setFullname(newDetails.getFullname());
       oldDetails.setPhoto(newDetails.getPhoto());
       userDAO.save(oldDetails);
    }
    @Override
    public List<User> getByQuery(String fullname){
        return userDAO.findAllByFullnameContainingOrderByFullname(fullname);
    }
    @Override
    public void changePassword(String username,String password){
      User user =  userDAO.findByUsername(username);
      user.setPassword(password);
      userDAO.save(user);
    }
}
