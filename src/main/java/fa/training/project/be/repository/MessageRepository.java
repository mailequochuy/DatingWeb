package fa.training.project.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fa.training.project.be.entities.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

	/**
     * Function: find messages by user_id & friend_id
     * @param user_id
     * @param friend_id
     * @return
     */
    @Query("select m from Message m where m.user_id = ?1 and m.friend_id = ?2")
    public List<Message> findMessage(Integer user_id, Integer friend_id);
    
    /**
     * Function: find messages by friend_id & user_id
     * @param user_id
     * @param friend_id
     * @return
     */
    @Query("select m from Message m where m.user_id = ?2 and m.friend_id = ?1")
    public List<Message> findMessageElse(Integer user_id, Integer friend_id);
    
    /**
     * Function: get messages user and friend sort by time
     * @param user_id
     * @param friend_id
     * @return
     */
    @Query("select m from Message m where m.user_id = ?1 and m.friend_id = ?2 or m.user_id = ?2 and m.friend_id = ?1 order by m.time asc")
    public List<Message> getMessages(Integer user_id, Integer friend_id);
}
