package fa.training.project.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.project.be.entities.Friends;
import fa.training.project.be.repository.FriendsRepository;

@Service
public class FriendsServiceImpl implements FriendsService{

    @Autowired
    FriendsRepository friendsRepository;
    
    /**
     * Function: create/save new friend
     * @param friends
     */
    @Override
    public void save(Friends friends) {
        // TODO Auto-generated method stub
        this.friendsRepository.save(friends);
    }

    /**
     * Function: find list friends by user_id
     * @param user_id
     * @return
     */
    @Override
    public List<Friends> findByUserId(Integer user_id) {
        // TODO Auto-generated method stub
        return this.friendsRepository.findByUserId(user_id);
    }
    
    /**
     * Function: find Friend by friend_id
     * @param user_id
     * @return
     */
    @Override
    public Friends findByFriendId(Integer user_id) {
        // TODO Auto-generated method stub
        return this.friendsRepository.findByFriendId(user_id);
    }

    /**
     * Function: find list Friends by user_id
     * @param user_id
     * @return
     */
    @Override
    public List<Friends> findFriends(Integer user_id) {
        // TODO Auto-generated method stub
        return this.friendsRepository.findFriends(user_id);
    }

    /**
     * Function: find list friends is_marked by user_id
     * @param user_id
     * @return
     */
    @Override
    public List<Friends> findFriendsMarked(Integer user_id) {
        // TODO Auto-generated method stub
        return this.friendsRepository.findFriendsMarked(user_id);
    }

    /**
     * Function: Find friend by friend_id & user_id
     * @param user_id
     * @param friend_id
     * @return
     */
    @Override
    public Friends findByUserIdAndFriendId(Integer user_id, Integer friend_id) {
        // TODO Auto-generated method stub
        return this.friendsRepository.findByUserIdAndFriendId(user_id, friend_id);
    }

    /**
     * Function: find list Friends who like user
     * @param user_id
     * @return
     */
    @Override
    public List<Friends> listLikeYou(Integer user_id) {
        // TODO Auto-generated method stub
        return this.friendsRepository.listLikeYou(user_id);
    }

    /**
     * Function: delete Friend by user_id
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        this.friendsRepository.deleteById(id);
    }
}
