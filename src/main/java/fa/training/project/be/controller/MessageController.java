package fa.training.project.be.controller;

import java.lang.annotation.Retention;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.project.be.entities.Friends;
import fa.training.project.be.entities.Message;
import fa.training.project.be.entities.User;
import fa.training.project.be.service.FriendsService;
import fa.training.project.be.service.MessageService;
import fa.training.project.be.service.UserService;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;
    
    @Autowired
    FriendsService friendsService;
    
    @Autowired
    UserService userService;
    
    /**
     * Function: Get message from client and create new message
     * @param session
     * @param model
     * @param msg
     * @return
     */
    @PostMapping("saveMessage")
    public @ResponseBody boolean saveMessage(HttpSession session,Model model,@RequestBody Message msg) {
        User userSession =  (User) session.getAttribute("user");
        if(userSession == null) {
            return false;
        }else {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            Message newMsg = new Message();
            newMsg.setUser_id(userSession.getId());
            newMsg.setFriend_id(msg.getFriend_id());
            newMsg.setMessage(msg.getMessage());
            newMsg.setTime(time);
            this.messageService.save(newMsg);
            return true;
        }
    }
    
    /**
     * Function: Get messages user & friend from db push to client
     * @param session
     * @param model
     * @param fr
     * @return
     */
    @PostMapping("getMess")
    public @ResponseBody List<Message>  getMess(HttpSession session, Model model, @RequestBody User fr) {
        User userSession =  (User) session.getAttribute("user");
        if(userSession == null) {
            return null;
        }else {
            return this.messageService.getMessages(userSession.getId(), fr.getId());
        }
    }
    
    /**
     * Function: return chat page
     * @param model
     * @param session
     * @return
     */
    @GetMapping("chats")
    public String chatPage(Model model, HttpSession session) {
        User userSession =  (User) session.getAttribute("user");
        if(userSession == null) {
            return "redirect:/login";
        }else {
            List<Friends> listLike = this.friendsService.listLikeYou(userSession.getId());
            if(listLike.size() == 0) {
                model.addAttribute("noListLikeYou","Nobody like you more ...");
                model.addAttribute("listLikeYou", listLike);
            }else {
                List<User> listLikeYou = new ArrayList<>();
                for (Friends fr : listLike) {
                    listLikeYou.add(this.userService.findById(fr.getUser_id()));
                }
                Collections.reverse(listLikeYou);
                model.addAttribute("listLikeYou", listLikeYou);
            }
            
            List<Friends> listFriends = this.friendsService.findFriends(userSession.getId());
            
            List<User> listUserFriends = new ArrayList<>();
            
            List<User> allUser = this.userService.findAll();
            
            for (User u : allUser) {
                for (Friends fr : listFriends) {
                    if(u.getId() == fr.getFriend_id()) {
                        listUserFriends.add(u);
                    }
                }
            }
            
            if(listUserFriends.size() == 0) {
                model.addAttribute("noList", "No matching friends yet ... ");
                
            }else {
                model.addAttribute("listUserFriends", listUserFriends);
            }
            
            List<Friends> listMark = this.friendsService.findFriendsMarked(userSession.getId());
            model.addAttribute("listMarked", listMark);
            model.addAttribute("user", userSession);
            return "users/chatpage";
        }
    }
    
}
