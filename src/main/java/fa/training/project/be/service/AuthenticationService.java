package fa.training.project.be.service;


import fa.training.project.be.entities.User;

public interface AuthenticationService {
    
    /**
     * Function: Create/save new user
     * @param user
     * @return 
     */
    public boolean RegisterUser(User user);
    
    /**
     * Function: Find user by email
     * @param email
     * @return
     */
    public User findByEmail(String email);
    
    
    /**
     * Function: get code to check for forget password
     * @param email
     * @return
     */
    public User getCode(String email);
    
}
