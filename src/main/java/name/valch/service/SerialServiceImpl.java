package name.valch.service;

import name.valch.entity.Serial;
import name.valch.entity.SerialsWithDates;
import name.valch.repository.SerialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SerialServiceImpl implements SerialService {

    @Autowired
    SerialRepository serialRepository;

    @Autowired
    SerialsWithDatesService serialsWithDatesService;

    @Override
    @Transactional
    public Serial save(Serial serial) {
        SerialsWithDates swd = new SerialsWithDates(serial.getName(), LocalDateTime.now());
        serialsWithDatesService.save(swd);
        return serialRepository.save(serial);
    }

    @Override
    public List<Serial> findAll() {
        return serialRepository.findAll();
    }

    @Override

    public Serial findOne(Long id) {
        return serialRepository.findOne(id);
    }

    @Override
    public List<Serial> findByPattern(String pattern) {
        return serialRepository.findByPattern(pattern);
    }

    @Override

    public void delete(Long id) {//admin
        serialRepository.delete(id);
    }

    @Override

    public Serial update(Serial serial) {
        return serialRepository.save(serial);
    }

    @Transactional
    public void deleteMultiple (Long[] ids) {

        List<Serial> selectedList1 = new ArrayList<>();
        List<SerialsWithDates> selectedList2 = new ArrayList<>();
        for (Long id:ids) {
            selectedList1.add(serialRepository.findById(id));
            selectedList2.add(serialsWithDatesService.findById(id));
        }

        for(Serial ser : selectedList1) {
            serialRepository.delete(ser);
        }
        for(SerialsWithDates swd : selectedList2) {
            serialsWithDatesService.delete(swd.getId());
        }
    }

    @Transactional
    @Override
    public Serial add (Serial addForm) {

        if (!serialRepository.findByPattern(addForm.getName()).isEmpty()) {
            Serial serial = new Serial();
            SerialsWithDates swd = new SerialsWithDates();

            serial.setName(addForm.getName());
            swd.setName(addForm.getName());
            swd.setDate(LocalDateTime.now());

            //    message = messageRepository.save(message);
            return serial;

        }
        else {
            return null;
        }
    }
}
