package name.valch.service;

import name.valch.entity.SerialWithDates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public interface SerialWithDatesService {

    SerialWithDates save(SerialWithDates serial);

    List<SerialWithDates> findAll();

    SerialWithDates findOne(Long id);

    SerialWithDates findById(Long id);

    SerialWithDates findByName(String name);

    void delete(Long id);

    SerialWithDates update(SerialWithDates serial);

    ArrayList<SerialWithDates> findByDateBetween(LocalDateTime date, LocalDateTime date2);

}
