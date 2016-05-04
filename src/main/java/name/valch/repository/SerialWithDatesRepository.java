package name.valch.repository;

import name.valch.entity.SerialWithDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Repository
public interface SerialWithDatesRepository extends JpaRepository<SerialWithDates, Long> {


    SerialWithDates findByName(String name);
    //@Query(value = "select c from SerialWithDates c where c.date between date1 and date2")
    ArrayList<SerialWithDates> findByDateBetween (LocalDateTime date1, LocalDateTime date2);
    SerialWithDates findById(Long id);
}
