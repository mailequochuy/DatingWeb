package fa.training.project.be.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.training.project.be.entities.User;
import fa.training.project.be.service.AuthenticationService;
import fa.training.project.be.service.UserService;

@Controller
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    
    @Autowired
    UserService userService;
    
    /**
     * Function: redirect register page
     * @param model
     * @return
     */
    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "users/register";
    }
    
    
    /**
     * Function: Create new user by register
     * @param model
     * @param user
     * @return
     */
    @PostMapping("registerUser")
    public String registerUser(Model model,@ModelAttribute User user) {
        User u = this.authenticationService.findByEmail(user.getEmail());
        if(this.authenticationService.RegisterUser(user) == true) {
            model.addAttribute("success", "Register user successfully! Login now!");
            return "users/login";
        } else {
            model.addAttribute("error", "Email already exists!");
            return "users/register";
        }
        
    }
    
    
    /**
     * Function: redirect login page 
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "users/login";
    }
    
    @GetMapping("home")
    public String home(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }else {
            model.addAttribute("user", user);
            return "users/index";
        }
    }
    
    @PostMapping("loginUser")
    public String loginUser(Model model,@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpSession session) {
        User u = this.authenticationService.findByEmail(user.getEmail());
        if(u == null) {
            model.addAttribute("error", "Email does not exist, please register!");
            return "users/login";
        }
        
        if(u.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", u);
            return "redirect:/home";
        }else {
            model.addAttribute("error", "Incorrect email or password!");
            return "users/login";
        }
    }
    
    /**
     * Function: delete session and log out
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    /**
     * Function: redirect forget password page
     * @return
     */
    @GetMapping("forgetpassword")
    public String forgetpassword() {
        return "users/forgetpassword";
    }
    
    
    /**
     * Function: Generate Code to get new password
     * @param session
     * @param email
     * @return
     */
    @PostMapping("getcode")
    public @ResponseBody User getCode(HttpSession session, @RequestBody User email) {
        User user = this.authenticationService.getCode(email.getEmail());
        if(user == null) {
            return null;
        }else {
            session.setAttribute("user", user);
            session.setAttribute("code", user.getCode());
            return user;
        }
    }
    
    /**
     * Function: Check Code and save new password for user
     * @param session
     * @param email
     * @return
     */
    @PostMapping("getpassword")
    public @ResponseBody User getPassword(HttpSession session, @RequestBody User email) {
        User user = (User) session.getAttribute("user");
        if(email.getCode().equals(session.getAttribute("code"))) {
            user.setPassword(email.getPassword());
            user.setCode(null);
            this.userService.save(user);
            session.removeAttribute("code");
            session.removeAttribute("user");
            return user;
        }else {
            return null;
        }
    }
    
    
    /**
     * 
     * @param session
     * @return
     */
    @PostMapping("checkSession")
    public @ResponseBody boolean checkSession(HttpSession session) {
        User userSession =  (User) session.getAttribute("user");
        if(userSession == null) {
            return false;
        }else {
            return true;
        }
    }
    
    
    @GetMapping("fragment")
    public String frag() {
    	return "users/layouts/home";
    }
    
    @GetMapping("call")
    public String call(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }else {
            model.addAttribute("user", user);
            return "users/call";
        }
    }
}
