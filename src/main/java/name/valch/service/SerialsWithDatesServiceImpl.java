package name.valch.service;

import name.valch.entity.SerialsWithDates;
import name.valch.repository.SerialsWithDatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class SerialsWithDatesServiceImpl implements SerialsWithDatesService {

    @Autowired
    SerialsWithDatesRepository serialsWithDatesRepository;

    @Override
    @Transactional
    public SerialsWithDates save(SerialsWithDates serial) {
        return serialsWithDatesRepository.save(serial);
    }

    @Override
    public List<SerialsWithDates> findAll() {
        return serialsWithDatesRepository.findAll();
    }

    @Override

    public SerialsWithDates findOne(Long id) {
        return serialsWithDatesRepository.findOne(id);
    }

    @Override

    public SerialsWithDates findById(Long id) {
        return serialsWithDatesRepository.findOne(id);
    }

    @Override
    public SerialsWithDates findByPattern(String pattern) {
        return serialsWithDatesRepository.findByPattern(pattern);
    }

    @Override

    public void delete(Long id) {//admin
        serialsWithDatesRepository.delete(id);
    }

    @Override

    public SerialsWithDates update(SerialsWithDates serial) {
        return serialsWithDatesRepository.save(serial);
    }

    @Override
    public List<SerialsWithDates> findByDate(LocalDateTime date1, LocalDateTime date2) {
        List <SerialsWithDates> serials = serialsWithDatesRepository.findByDate(date1, date2);
        return serials;
    }

}
