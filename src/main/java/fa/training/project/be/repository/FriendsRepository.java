package fa.training.project.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fa.training.project.be.entities.Friends;

@Repository
public interface FriendsRepository extends CrudRepository<Friends, Integer>{
    
    /**
     * Function: find list friends by user_id
     * @param user_id
     * @return
     */
    @Query("select f from Friends f where f.user_id = ?1")
    public List<Friends> findByUserId(Integer user_id);
    
    /**
     * Function: find Friend by friend_id
     * @param user_id
     * @return
     */
    @Query("select f from Friends f where f.friend_id = ?1")
    public Friends findByFriendId(Integer user_id);
    
    /**
     * Function: Find friend by friend_id & user_id
     * @param user_id
     * @param friend_id
     * @return
     */
    @Query("select f from Friends f where f.friend_id = ?1 and f.user_id = ?2 ")
    public Friends findByUserIdAndFriendId(Integer user_id, Integer friend_id);
    
    /**
     * Function: find list Friends by user_id
     * @param user_id
     * @return
     */
    @Query("select f from Friends f where f.user_id = ?1 and is_friend=true")
    public List<Friends> findFriends(Integer user_id);
    
    /**
     * Function: find list friends is_marked by user_id
     * @param user_id
     * @return
     */
    @Query("select f from Friends f where f.user_id = ?1 and is_marked=true")
    public List<Friends> findFriendsMarked(Integer user_id);
    
    /**
     * Function: find list Friends who like user
     * @param user_id
     * @return
     */
    @Query("select f from Friends f where f.friend_id = ?1 and is_friend = false")
    public List<Friends> listLikeYou(Integer user_id);
}
