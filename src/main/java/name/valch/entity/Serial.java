package name.valch.entity;

import javax.persistence.*;


@Entity
public class Serial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String name;


    public Serial() {
    }

    public Serial(String name) {
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


    @Override
    public String toString() {
        return "Serial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
