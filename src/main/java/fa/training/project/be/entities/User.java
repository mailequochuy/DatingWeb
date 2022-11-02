package fa.training.project.be.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 4L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;
    
    @Column(name = "role_id")
    private int role_id;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "phoneNumber")
    private String phoneNumber;
    
    @Column(name = "age")
    private int age;
    
    @Column(name = "avatar")
    private String avatar;
    
    @Column(name = "hobby")
    private String hobby;
    
    @Column(name = "is_blocked")
    private boolean is_blocked;
    
    @Column(name = "is_active")
    private boolean is_active;
    
    @Column(name = "descriptions")
    private String descriptions;
    
    @Column(name = "code")
    private String code;
    
    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(int id, String email, String userName, String password, int role_id, String gender, String phoneNumber,
            int age, String avatar, String hobby, boolean is_blocked, boolean is_active, String descriptions,
            String code) {
        super();
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role_id = role_id;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.avatar = avatar;
        this.hobby = hobby;
        this.is_blocked = is_blocked;
        this.is_active = is_active;
        this.descriptions = descriptions;
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    
    

    
}
