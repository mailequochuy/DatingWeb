package fa.training.project.be.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_friends")
public class Friends {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "user_id")
    private int user_id;
    
    @Column(name = "friend_id")
    private int friend_id;
    
    @Column(name = "is_marked")
    private boolean is_marked;
    
    @Column(name = "is_friend")
    private boolean is_friend;
    
    public Friends(int id, int user_id, int friend_id, boolean is_marked, boolean is_friend) {
        super();
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.is_marked = is_marked;
        this.is_friend = is_friend;
    }

    public boolean isIs_friend() {
        return is_friend;
    }

    public void setIs_friend(boolean is_friend) {
        this.is_friend = is_friend;
    }

    public Friends() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public boolean isIs_marked() {
        return is_marked;
    }

    public void setIs_marked(boolean is_marked) {
        this.is_marked = is_marked;
    }
    
}
