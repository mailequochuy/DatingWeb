package fa.training.project.be.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.project.be.entities.User;
import fa.training.project.be.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepository;
    
    /**
     * Function: get All users
     * @return
     */
    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return (List<User>) userRepository.findAll();
    }
    
    /**
     * Function: create/save user
     * @param user
     */
    @Override
    public void save(User user) {
        // TODO Auto-generated method stub
        userRepository.save(user);
    }
    
    /**
     * Function: find user by ID
     * @param id
     * @return
     */
    @Override
    public User findById(int id) {
        // TODO Auto-generated method stub
        return this.userRepository.findById(id).orElse(null);
    }
    
    /**
     * Function: Delete user by id
     * @param id
     */
    @Override
    public void deleteById(int id) {
        // TODO Auto-generated method stub
        this.userRepository.deleteById(id);
        
    }
    
    /**
     * Function: find users by age & gender
     * @param age
     * @param gender
     * @return
     */
    @Override
    public List<User> findUsersByAgeAndGender(Integer age, String gender) {
        // TODO Auto-generated method stub
        return this.userRepository.findUsersByAgeAndGender(age, gender);
    }

    /**
     * Function: find users by age & gender with out user_id
     * @param age
     * @param gender
     * @param id
     * @return
     */
    @Override
    public List<User> findingUserByAgeAndGender(Integer age, String gender, Integer id) {
        // TODO Auto-generated method stub
        return this.userRepository.findingUserByAgeAndGender(age, gender, id);
    }
    
    /**
     * Function: find user by Email
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        return this.userRepository.findByEmail(email);
    }

    
    /**
     * Function: find user to show in finding page
     * @param minAge
     * @param maxAge
     * @param gender
     * @param id
     * @return
     */
    @Override
    public List<User> findFriends(Integer minAge, Integer maxAge, String gender, Integer id) {
        // TODO Auto-generated method stub
        return this.userRepository.findFriends(minAge, maxAge, gender, id);
    }

    /**
     * Function: update user's information in profile page
     * @param user
     * @return
     */
    @Override
    public User updateProfile(User user, User userSession) {
        // TODO Auto-generated method stub
        
        //get userSession
        userSession.setPhoneNumber(user.getPhoneNumber());
        userSession.setAge(user.getAge());
        userSession.setDescriptions(user.getDescriptions());
        userSession.setGender(user.getGender());
        
        this.userRepository.save(userSession);
        return userSession;
    }

    /**
     * Function: active or inactive user
     * @param user
     * @return
     */
    @Override
    public User activeUser(User user) {
        // TODO Auto-generated method stub
        if(user.isIs_active() == true) {
            user.setIs_active(false);
        }else {
            user.setIs_active(true);
        }
        this.userRepository.save(user);
        return user;
    }
}
