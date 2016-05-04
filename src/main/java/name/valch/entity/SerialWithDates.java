package name.valch.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class SerialWithDates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime date;

    @Column
    private String dateString;

    public String getDateString() {
        return date.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setDateString(LocalDateTime date) {
        this.dateString = date.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public SerialWithDates() {
    }

    public SerialWithDates(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
        setDateString(date);
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

    @Override
    public String toString() {
        return "Serials{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + dateString +'}';
    }

}
