package name.valch.service;

import name.valch.entity.SerialWithDates;
import name.valch.entity.User;
import name.valch.entity.UserProfile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public interface SerialWithDatesService {

 //   SerialWithDates save(SerialWithDates serial);

    Iterable<SerialWithDates> findAll();

    Iterable<SerialWithDates> findAllForUser(User user);

    SerialWithDates findOne(Long id);

    SerialWithDates findById(Long id);

    SerialWithDates findByName(String name);
    Iterable <SerialWithDates> findByNameContaining (String name);

    void delete(SerialWithDates swd, UserProfile userProfile);

    SerialWithDates update(SerialWithDates serial);

    SerialWithDates save (SerialWithDates serial);

    Iterable<SerialWithDates> findByDateBetween(LocalDateTime date, LocalDateTime date2);
    void deleteMultiple (Long[] ids, User user);
    public SerialWithDates add (SerialWithDates addForm, User user);

}
