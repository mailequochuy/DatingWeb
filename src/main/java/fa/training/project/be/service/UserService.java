package fa.training.project.be.service;

import java.util.List;

import fa.training.project.be.entities.User;


public interface UserService {
	/**
    * Function: get All users
    * @return
    */
    public List<User> findAll();
    
    /**
     * Function: create/save user
     * @param user
     */
    public void save(User user);
    
    /**
     * Function: find user by ID
     * @param id
     * @return
     */
    public User findById(int id);
    
    /**
     * Function: Delete user by id
     * @param id
     */
    public void deleteById(int id);
    
    /**
     * Function: find user by Email
     * @param email
     * @return
     */
    public User findByEmail(String email);
    
    /**
     * Function: find users by age & gender
     * @param age
     * @param gender
     * @return
     */
    public List<User> findUsersByAgeAndGender(Integer age, String gender);
    
    /**
     * Function: find users by age & gender with out user_id
     * @param age
     * @param gender
     * @param id
     * @return
     */
    public List<User> findingUserByAgeAndGender(Integer age, String gender, Integer id);
    
    /**
     * Function: find user to show in finding page
     * @param minAge
     * @param maxAge
     * @param gender
     * @param id
     * @return
     */
    public List<User> findFriends(Integer minAge, Integer maxAge, String gender, Integer id);
    
    /**
     * Function: update user's information in profile page
     * @param user
     * @return
     */
    public User updateProfile(User user, User userSession);
    
    /**
     * Function: active or inactive user
     * @param user
     * @return
     */
    public User activeUser(User user);
     
}
