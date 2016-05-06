package name.valch.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class SerialWithDates {

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public SerialWithDates() {
    }

    public SerialWithDates(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
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

    @Override
    public String toString() {
        return "SerialWithDates{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", date=" + date +
                '}';
    }

}
