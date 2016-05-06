package name.valch.service;

import name.valch.entity.SerialWithDates;
import name.valch.repository.SerialWithDatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SerialWithDatesServiceImpl implements SerialWithDatesService {

    @Autowired
    SerialWithDatesRepository serialWithDatesRepository;

    @Override
    @Transactional
    public SerialWithDates save(SerialWithDates serial) {
        return serialWithDatesRepository.save(serial);
    }

    @Override
    public List<SerialWithDates> findAll() {
        return serialWithDatesRepository.findAll();
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
    public List <SerialWithDates> findByName(String name) {
        return serialWithDatesRepository.findByName(name);
    }

    @Override
    public List <SerialWithDates> findByNameContaining(String name) {
        return serialWithDatesRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public void delete(Long id) {//admin
        serialWithDatesRepository.delete(id);
    }

    @Override
    public SerialWithDates update(SerialWithDates serial) {
        return serialWithDatesRepository.save(serial);
    }

    @Override
    public ArrayList<SerialWithDates> findByDateBetween(LocalDateTime date1, LocalDateTime date2) {
        ArrayList<SerialWithDates> serials = serialWithDatesRepository.findByDateBetween(date1, date2);
        return serials;
    }

}
