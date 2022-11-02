package fa.training.project.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.project.be.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    
    /**
     * Function: find user by Email
     * @param email
     * @return
     */
    @Query("select u from User u where u.email = ?1")
    public User findByEmail(String email);
    
    /**
     * Function: find users by age & gender
     * @param age
     * @param gender
     * @return
     */
    @Query("select u from User u where u.age = ?1 and u.gender = ?2 and u.is_active = true")
    public List<User> findUsersByAgeAndGender(Integer age, String gender);
    
    /**
     * Function: find users by age & gender with out user_id
     * @param age
     * @param gender
     * @param id
     * @return
     */
    @Query("select u from User u where u.age = ?1 and u.gender = ?2 and u.is_active = true and u.id != ?3")
    public List<User> findingUserByAgeAndGender(Integer age, String gender, Integer id);
    
    
    /**
     * Function: find list user to show in finding page and list is not like before ( check in table user_friend )
     * @param minAge
     * @param maxAge
     * @param gender
     * @param id
     * @return
     */
    @Query("select u from User u left join Friends f on f.user_id = ?4 and f.friend_id = u.id where u.age between ?1 and ?2 and u.gender = ?3 and u.is_active = true and u.id != ?4 and f.user_id is null")
    public List<User> findFriends(Integer minAge, Integer maxAge, String gender, Integer id);
}
