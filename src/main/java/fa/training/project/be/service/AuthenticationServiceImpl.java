package fa.training.project.be.service;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import fa.training.project.be.entities.User;
import fa.training.project.be.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    UserRepository userRepository;
    
    /**
     * Function: Create/save new user
     * @param user
     * @return 
     */
    @Override
    public boolean RegisterUser(User user) {
        // TODO Auto-generated method stub
        User u = this.userRepository.findByEmail(user.getEmail());
        if(u == null) {
            user.setRole_id(2);
            user.setIs_active(true);
            user.setDescriptions("Descriptions ... ");
            userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * Function: Find user by email
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        return this.userRepository.findByEmail(email);
    }
    
    /**
     * Function: check email & generate code to check
     * @param email
     * @return
     */
    @Override
    public User getCode(String email) {
        // TODO Auto-generated method stub
        User user = this.userRepository.findByEmail(email);
        if(user == null) {
            return null;
        }else {
            Random rnd = new Random();
            String code = String.valueOf(rnd.nextInt(999999)+ 100000) ;
            user.setCode(code);
            this.userRepository.save(user);
            System.out.println(user.getCode());
            return user;
        }
    }

}
