package name.valch.entity;

import name.valch.SeasonvarApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
public class User implements Serializable {

    public static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String login;

    @OneToOne (mappedBy = "userName", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserProfile profile;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private String email;

    public User() {
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public User(User user) {
        super();
        this.login = user.getLogin();
        this.profile=user.getProfile();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.password = user.getPassword();

    }

    public User(String login, String password, String role, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
