package fa.training.project.be.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.project.be.entities.Message;
import fa.training.project.be.repository.MessageRepository;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;
     
    /**
     * Function: create&save new message
     * @param msg
     */
    @Override
    public void save(Message msg) {
        // TODO Auto-generated method stub
        this.messageRepository.save(msg);
    }

    /**
     * Function: delete user by ID
     * @param id
     */
    @Override
    public List<Message> findMessage(Integer user_id, Integer friend_id) {
        // TODO Auto-generated method stub
        return this.messageRepository.findMessage(user_id, friend_id);
    }

    /**
     * Function: find messages by user_id & friend_id
     * @param user_id
     * @param friend_id
     * @return
     */
    @Override
    public List<Message> findMessageElse(Integer user_id, Integer friend_id) {
        // TODO Auto-generated method stub
        return this.messageRepository.findMessageElse(user_id, friend_id);
    }
    
    /**
     * Function: find messages by friend_id & user_id
     * @param user_id
     * @param friend_id
     * @return
     */
    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        this.messageRepository.deleteById(id);
    }

    @Override
    public List<Message> getMessages(Integer user_id, Integer friend_id) {
        // TODO Auto-generated method stub
        
        return this.messageRepository.getMessages(user_id, friend_id);
    }

}
