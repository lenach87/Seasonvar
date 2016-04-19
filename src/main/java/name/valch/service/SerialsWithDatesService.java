package name.valch.service;

import name.valch.entity.SerialsWithDates;

import java.time.LocalDateTime;
import java.util.List;


public interface SerialsWithDatesService {

    SerialsWithDates save(SerialsWithDates serial);

    List<SerialsWithDates> findAll();

    SerialsWithDates findOne(Long id);

    SerialsWithDates findById(Long id);

    SerialsWithDates findByPattern(String pattern);

    void delete(Long id);

    SerialsWithDates update(SerialsWithDates serial);

    List<SerialsWithDates> findByDate(LocalDateTime date, LocalDateTime date2);

}
