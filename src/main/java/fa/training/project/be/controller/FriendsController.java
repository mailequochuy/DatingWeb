package fa.training.project.be.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.project.be.entities.Friends;
import fa.training.project.be.entities.Message;
import fa.training.project.be.entities.User;
import fa.training.project.be.service.FriendsService;
import fa.training.project.be.service.MessageService;

@Controller
public class FriendsController {

    @Autowired
    FriendsService friendsService;
    
    @Autowired
    MessageService messageService;
    
    /**
     * Function: Get friend_id, check is_friend, create new friend
     * @param model
     * @param session
     * @param friend
     * @return
     */
    @PostMapping("likefriend")
    public @ResponseBody boolean likeFriend(Model model, HttpSession session, @RequestBody Friends friend) {
        User userSession =  (User) session.getAttribute("user");
            Friends fr = new Friends();
            fr.setUser_id(userSession.getId());
            fr.setFriend_id(friend.getFriend_id());
            Friends friendfr = this.friendsService.findByUserIdAndFriendId(userSession.getId(), friend.getFriend_id());
            if(friendfr == null) {
                fr.setIs_friend(false);
            }else {
                fr.setIs_friend(true);
                friendfr.setIs_friend(true);
            }
            
            fr.setIs_marked(false);
            
            this.friendsService.save(fr);
            this.friendsService.save(friendfr);
            
            return true;
}
    /**
     * Function: Get friend_id and change field is_marked 
     * @param session
     * @param user_id
     * @return
     */
    @PostMapping("markedfriend")
    public @ResponseBody List<Friends> markedFriend(HttpSession session, @RequestBody User user_id) {
        User userSession =  (User) session.getAttribute("user");
        Friends fr = this.friendsService.findByFriendId(user_id.getId());
        if(fr.isIs_marked() == false) {
            fr.setIs_marked(true);
            this.friendsService.save(fr);
            List<Friends> list = this.friendsService.findFriendsMarked(userSession.getId());
            return list;
        }else {
            fr.setIs_marked(false);
            this.friendsService.save(fr);
            List<Friends> list = this.friendsService.findFriendsMarked(userSession.getId());
            return list;
        }
    }
    
    /**
     * Function: Get friend_id and delete friend both user & friend
     * @param session
     * @param fr
     * @return
     */
    @PostMapping("delete")
    public @ResponseBody boolean delete(HttpSession session, @RequestBody User fr) {
        User userSession =  (User) session.getAttribute("user");
        if(userSession == null) {
            return false;
        }else {
            Friends fr1 = this.friendsService.findByUserIdAndFriendId(userSession.getId(), fr.getId());
            Friends fr2 = this.friendsService.findByUserIdAndFriendId(fr.getId(), userSession.getId());
            this.friendsService.deleteById(fr1.getId());
            this.friendsService.deleteById(fr2.getId());
            List<Message> user_mess = this.messageService.findMessage(userSession.getId(), fr.getId());
            List<Message> fr_mess = this.messageService.findMessageElse(userSession.getId(), fr.getId());
            user_mess.addAll(fr_mess);
            for (Message message : user_mess) {
                this.messageService.deleteById(message.getId());
            }
            return true;
        }
    }


}
