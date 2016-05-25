package name.valch.entity;

import name.valch.SeasonvarApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lena on 5/19/16.
 */

@Entity
public class UserProfile implements Serializable {

    public static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);

    public UserProfile() {
    }

    public UserProfile(User user) {

        this.userName = user;
        this.accountName = (new StringBuilder().append(user.getLogin()).append("_profile")).toString();
        this.serials=new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    String accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User userName;


    @ManyToMany(mappedBy = "profiles", fetch = FetchType.EAGER)
    private Set<SerialWithDates> serials = new HashSet<>();

    public Set<SerialWithDates> getSerials() {
        return new HashSet(serials);
    }

    public void addSerial(SerialWithDates serial) {
        //    log.info ("serials are not null");
            serials.add(serial);
          //  log.info ("serial.users are not null");
       //     serial.addUser(this);

    }

    public void removeSerial(SerialWithDates serial) {
        serials.remove(serial);
      //  log.info ("The list of serials looks like empty  " + serials.size());
     //   serial.removeUser(this);
     //   log.info ("The list of serials looks like empty  " + serials.isEmpty());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", userName=" + userName +
                ", serials=" + serials +
                '}';
    }
}
