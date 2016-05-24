package name.valch.repository;

import name.valch.entity.SerialWithDates;
import name.valch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface SerialWithDatesRepository extends JpaRepository<SerialWithDates, Long> {

 //   @PostAuthorize("hasPermission(returnObject, 'read')")
    SerialWithDates findByName(String name);
    @PostAuthorize("hasPermission(returnObject, 'read')")
    Iterable<SerialWithDates> findByNameIgnoreCaseContaining(String name);
 //   @PostAuthorize("hasPermission(returnObject, 'read')")
    Iterable<SerialWithDates> findByDateBetween (LocalDateTime date1, LocalDateTime date2);
    @PostAuthorize("hasPermission(returnObject, 'read')")
    SerialWithDates findById(Long id);
}
