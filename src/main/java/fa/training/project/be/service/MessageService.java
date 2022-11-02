package fa.training.project.be.service;

import java.util.List;

import fa.training.project.be.entities.Message;

public interface MessageService {
    /**
     * Function: create&save new message
     * @param msg
     */
    public void save(Message msg);
    
    /**
     * Function: delete user by ID
     * @param id
     */
    public void deleteById(Integer id);
    
    /**
     * Function: find messages by user_id & friend_id
     * @param user_id
     * @param friend_id
     * @return
     */
    public List<Message> findMessage(Integer user_id, Integer friend_id);
    
    /**
     * Function: find messages by friend_id & user_id
     * @param user_id
     * @param friend_id
     * @return
     */
    public List<Message> findMessageElse(Integer user_id, Integer friend_id);
    
    
    /**
     * Function: get messages user and friend sort by time
     * @param user_id
     * @param friend_id
     * @return
     */
    public List<Message> getMessages(Integer user_id, Integer friend_id);
}
