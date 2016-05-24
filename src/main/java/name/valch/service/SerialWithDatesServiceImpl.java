package name.valch.service;

import name.valch.SeasonvarApplication;
import name.valch.entity.SerialWithDates;
import name.valch.entity.User;
import name.valch.entity.UserProfile;
import name.valch.repository.SerialWithDatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class SerialWithDatesServiceImpl implements SerialWithDatesService {

    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);
    @Autowired
    private SerialWithDatesRepository serialWithDatesRepository;

    @Override
    public List<SerialWithDates> findAll() {
        return serialWithDatesRepository.findAll();
    }

    @Override
    public Iterable<SerialWithDates> findAllForUser(User user) {

        List <SerialWithDates> allSerials = serialWithDatesRepository.findAll();
        ArrayList <SerialWithDates> necessarySerials = new ArrayList<>();
        for (SerialWithDates swd:allSerials) {
            Collection<UserProfile> allUsers = swd.getProfiles().values();
            for (UserProfile u:allUsers) {
                if (u.getUserName().getLogin().equals(user.getLogin())) {
                    log.info("Serials for user: " + user.getLogin() + " name of serial:" + swd.getName());
                    necessarySerials.add(swd);
                }
            }
        }
        return necessarySerials;
    }

    @Override

    public SerialWithDates findOne(Long id) {
        return serialWithDatesRepository.findOne(id);
    }

    @Override

    public SerialWithDates findById(Long id) {
        return serialWithDatesRepository.findOne(id);
    }

    @Override
    public SerialWithDates findByName(String name) {
        return serialWithDatesRepository.findByName(name);
    }

    @Override
    public Iterable<SerialWithDates> findByNameContaining(String name) {
        return serialWithDatesRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public void delete(SerialWithDates swd, UserProfile userProfile) {
        log.info ("Serial to delete user  " + swd.getName());
        swd.removeUser(userProfile);
    }

    @Override
    public SerialWithDates update(SerialWithDates serial) {
        return serialWithDatesRepository.save(serial);
    }

    @Override
    public Iterable<SerialWithDates> findByDateBetween(LocalDateTime date1, LocalDateTime date2) {
        return serialWithDatesRepository.findByDateBetween(date1, date2);
    }


    @Override
    @Transactional
    public SerialWithDates save(SerialWithDates serial) {
        return serialWithDatesRepository.save(serial);
    }

    @Transactional
    public void deleteMultiple (Long[] ids, User user) {

        List<SerialWithDates> selectedList2 = new ArrayList<>();
        for (Long id:ids) {
            selectedList2.add(serialWithDatesRepository.findById(id));
        }
        for(SerialWithDates swd : selectedList2) {
            delete(swd, user.getProfile());
       log.info("ToDelete multiple for user " + user.getLogin() + "");
            if (swd.getProfiles().isEmpty()) {
                serialWithDatesRepository.delete(swd);
            }
        }
    }

    @Transactional
    @Override
    public SerialWithDates add (SerialWithDates addForm, User user) {

        if (serialWithDatesRepository.findByName(addForm.getName())==null) {
            SerialWithDates swd = new SerialWithDates(addForm.getName(), LocalDateTime.now());
            swd.addUser(user.getProfile());
            for (UserProfile u: swd.getProfiles().values()) {
            log.info ("The list of users looks like "+ u.getUserName().getLogin());
            }
            return  serialWithDatesRepository.save(swd);
        }
        else {
            Set <SerialWithDates> serialsWD = new HashSet<>(user.getProfile().getSerials());
            ArrayList <String> names = new ArrayList<>();
            for (SerialWithDates s:serialsWD) {
                names.add(s.getName());
            }
            if (names.contains(addForm.getName())) {
                return null;
            }
            else {
                SerialWithDates swd = serialWithDatesRepository.findByName(addForm.getName());
                swd.addUser(user.getProfile());
                return update(swd);
            }
        }
    }

}
