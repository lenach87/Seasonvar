package name.valch.repository;

import name.valch.entity.Serial;
import name.valch.entity.SerialsWithDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface SerialsWithDatesRepository extends JpaRepository<SerialsWithDates, Long> {

    @Query("select c from SerialsWithDates c where c.name like :pattern")
    SerialsWithDates findByPattern(String pattern);
    @Query("select c from SerialsWithDates c where c.date between date1 and date2")
    List <SerialsWithDates> findByDate (LocalDateTime date1, LocalDateTime date2);

    SerialsWithDates findById(Long id);
}
