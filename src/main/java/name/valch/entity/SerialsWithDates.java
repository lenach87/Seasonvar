package name.valch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;



@Entity
public class SerialsWithDates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime date;

    public SerialsWithDates() {
    }

    public SerialsWithDates(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
    }

    public SerialsWithDates(String name) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Serials{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +'}';
    }

}
