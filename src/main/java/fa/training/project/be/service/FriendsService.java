package fa.training.project.be.service;

import java.util.List;

import fa.training.project.be.entities.Friends;

public interface FriendsService {
    /**
     * Function: find list friends by user_id
     * @param user_id
     * @return
     */
    public List<Friends> findByUserId(Integer user_id);
    
    /**
     * Function: create/save new friend
     * @param friends
     */
    public void save(Friends friends);
    
    /**
     * Function: find Friend by friend_id
     * @param user_id
     * @return
     */
    public Friends findByFriendId(Integer user_id);
    
    /**
     * Function: find list Friends by user_id
     * @param user_id
     * @return
     */
    public List<Friends> findFriends(Integer user_id);
    
    /**
     * Function: find list friends is_marked by user_id
     * @param user_id
     * @return
     */
    public List<Friends> findFriendsMarked(Integer user_id);
    
    /**
     * Function: Find friend by friend_id & user_id
     * @param user_id
     * @param friend_id
     * @return
     */
    public Friends findByUserIdAndFriendId(Integer user_id, Integer friend_id);
    
    /**
     * Function: find list Friends who like user
     * @param user_id
     * @return
     */
    public List<Friends> listLikeYou(Integer user_id);
    
    /**
     * Function: delete Friend by user_id
     * @param id
     */
    public void deleteById(Integer id);
}
