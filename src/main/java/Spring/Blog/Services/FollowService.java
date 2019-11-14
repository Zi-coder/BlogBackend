package Spring.Blog.Services;

import Spring.Blog.Model.Follow;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface FollowService {
    List<Follow> getFollowing(Principal principal);

    String newFollow(Long following_id, Principal principal);

    String unFollow(Long following_id, Principal principal);

    List<Follow> getFollowers(Principal principal);

    Long getFollowerCount(Long id);

    Long getFollowingCount(Long id);

    void deleteFollower(Long id, Principal principal);
}
