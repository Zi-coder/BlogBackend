package Spring.Blog.Services;

import Spring.Blog.DAO.FollowDAO;
import Spring.Blog.DAO.UserDAO;
import Spring.Blog.Model.Follow;
import Spring.Blog.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class FollowServiceImplementation implements FollowService {
    @Autowired
    FollowDAO followDAO;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    UserDAO userDAO;
    @Override
    public List<Follow> getFollowing(Principal principal){
      return followDAO.findAllByUser(currentUserService.currentUser(principal));
    }
    @Override
    public String newFollow(Long following_id, Principal principal){
        Follow follow = new Follow();
        follow.setFollowing(userDAO.findById(following_id).get());
        follow.setUser(currentUserService.currentUser(principal));
        followDAO.save(follow);
        return "\"You are now Following " + follow.getFollowing().getFullname() + "\"";
    }

    @Override
    public String unFollow(Long following_id, Principal principal){
        Follow follow = followDAO.findByUserAndFollowing(currentUserService.currentUser(principal),userDAO.findById(following_id).get());
        followDAO.delete(follow);
        return "\"Unfollow Successfull\"";
    }
    @Override
    public List<Follow> getFollowers(Principal principal){
        return followDAO.findAllByFollowing(currentUserService.currentUser(principal));
    }
    @Override
    public Long getFollowerCount(Long id){
        return followDAO.countAllByFollowing(userDAO.findById(id).get());
    }
    @Override
    public Long getFollowingCount(Long id){
        return followDAO.countAllByUser(userDAO.findById(id).get());
    }
    @Override
    public void deleteFollower(Long id,Principal principal){
        followDAO.delete(followDAO.findByUserAndFollowing( userDAO.findById(id).get(),currentUserService.currentUser(principal) ));
    }
}
