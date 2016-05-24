package name.valch.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import name.valch.SeasonvarApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


@Entity
public class SerialWithDates implements Serializable {

    public static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String link;

    @Column
    private LocalDateTime date;

    @Column
    private ArrayList<String> fullNewText;

    @ManyToMany (fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable (name = "serials_users")
    @MapKey(name = "accountName")
    private Map<String, UserProfile> profiles = new HashMap<String, UserProfile>();

    public void setProfiles(Map<String, UserProfile> profiles) {
        this.profiles = profiles;
    }

    public SerialWithDates() {
    }

    public SerialWithDates(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
        this.profiles = new HashMap<String, UserProfile>();
    }

    public SerialWithDates(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<String> getFullNewText() {
        return fullNewText;
    }

    public void setFullNewText(ArrayList<String> fullNewText) {
        this.fullNewText = fullNewText;
    }

    public void addUser(UserProfile user) {

        profiles.put(user.getAccountName(), user);
        user.addSerial(this);
    }

    public void removeUser(UserProfile user) {

        profiles.remove(user.getAccountName());
        user.removeSerial(this);
        log.info ("The list of users looks like empty" + profiles.isEmpty());
    }

    public Map<String, UserProfile> getProfiles() {
        return profiles;
    }

    @Override
    public String toString() {
        return "SerialWithDates{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", date=" + date +
                ", fullNewText='" + fullNewText + '\'' +
                '}';
    }
}
