package fa.training.project.be.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.node.ObjectNode;

import fa.training.project.be.entities.Friends;
import fa.training.project.be.entities.User;
import fa.training.project.be.service.FriendsService;
import fa.training.project.be.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    
    @Autowired
    FriendsService friendsService;
    /**
     * Function: Check avatar is_null
     * @param session
     * @return
     */
    @PostMapping("checkAva")
    public @ResponseBody boolean checkAva(HttpSession session) {
        User userSession = (User) session.getAttribute("user");
        if(userSession.getAvatar() == null){
            return false;
        }else {
            return true;
        }
    }
    
    /**
     * Function: Get gender, min&max age and find list user from db
     * @param session
     * @param model
     * @param obj
     * @return
     */
    @PostMapping("findFriends")
    public @ResponseBody List<User> findFriends(HttpSession session, Model model,@RequestBody ObjectNode obj){
        User userSession =  (User) session.getAttribute("user");
        return this.userService.findFriends(obj.get("minAge").asInt(), obj.get("maxAge").asInt(), obj.get("gender").asText(), userSession.getId());
    }
    
    /**
     * Function: redirect profile page
     * @param model
     * @param session
     * @return
     */
    @GetMapping("profile")
    public String detaisUser(Model model, HttpSession session) {
        User userSession =  (User) session.getAttribute("user");
        if(userSession == null) {
            return "redirect:/login";
        }else {
            model.addAttribute("user", userSession);
            return "users/users/profile";
        }
        
    }
    
    /**
     * Function: get friend_id and show friend's profile
     * @param model
     * @param session
     * @param id
     * @return
     */
    @GetMapping("profile/{id}")
    public String profile(Model model, HttpSession session, @PathVariable("id") Integer id) {
        User userSession = (User) session.getAttribute("user");
        if(userSession == null) {
            return "redirect:/login";
        }else {
            User fr_user = this.userService.findById(id);
            model.addAttribute("friend", fr_user);
            model.addAttribute("user", userSession);
            return "users/users/friendprofile";
        }
    }
    
    /**
     * Function: Get user from component & update user
     * @param model
     * @param session
     * @param user
     * @return
     */
    @PostMapping("updateProfile")
    public @ResponseBody User updateProfile(Model model, HttpSession session, @RequestBody User user ) {
        User userSession = (User) session.getAttribute("user");
        User u = this.userService.updateProfile(user, userSession);
        session.setAttribute("user", u);
            return u;
    }
    
    /**
     * Function: Get user from component, no changes return old user
     * @param session
     * @param user
     * @return
     */
    @PostMapping("noUpdate")
    public @ResponseBody User noUpdate(HttpSession session, @RequestBody User user) {
        User userSession =  (User) session.getAttribute("user");
        return userSession;
    }
    
    
    /**
     * Function: update is_active true/false for user
     * @param model
     * @param session
     * @return
     */
    @PostMapping("active")
    public @ResponseBody User activeUser(Model model, HttpSession session, @RequestBody User userSession) {
            session.removeAttribute("user");
            User user = this.userService.activeUser(userSession);
            session.setAttribute("user", user);
            return user;
    }
    
    /**
     * Function: save avatar by Base64
     * @param session
     * @param multipartFile
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("saveavatar")
    public @ResponseBody User saveAvatar(HttpSession session, @RequestParam("avatar") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
       User userSession =  (User) session.getAttribute("user");
       userSession.setAvatar(null);
       this.userService.save(userSession);
       session.invalidate();
       HttpSession newSession = request.getSession();
       byte[] avatar = new byte[0];
       avatar = Base64.encodeBase64(multipartFile.getBytes());
       String result = new String(avatar);
       userSession.setAvatar(result);
       this.userService.save(userSession);
       newSession.setAttribute("user", userSession);
       return userSession;
    }
    
    /**
     * Function: Preview avatar in profile page before update or cancel
     * @param session
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("previewImg")
    public @ResponseBody String previewImg(HttpSession session, @RequestParam("avatar") MultipartFile multipartFile) throws IOException {
        byte[] avatar = new byte[0];
        avatar = Base64.encodeBase64(multipartFile.getBytes());
        String result = new String(avatar);
        return result;
    }
    
    
    @PostMapping("blockUser")
    public @ResponseBody boolean blockUser(HttpSession session, @RequestBody User user) {
        User userSession =  (User) session.getAttribute("user");
        if(userSession == null) {
            return false;
        }else {
        	this.userService.blockUser(user);
            return true;
        }
    }
    
}
