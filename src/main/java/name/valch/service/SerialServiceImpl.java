package name.valch.service;

import name.valch.entity.Serial;
import name.valch.entity.SerialWithDates;
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
    SerialWithDatesService serialWithDatesService;

    @Override
    @Transactional
    public Serial save(Serial serial) {
        SerialWithDates swd = new SerialWithDates(serial.getName(), LocalDateTime.now());
        serialWithDatesService.save(swd);
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
    public List<Serial> findByName(String name) {
        return serialRepository.findByName(name);
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
        List<SerialWithDates> selectedList2 = new ArrayList<>();
        for (Long id:ids) {
            selectedList1.add(serialRepository.findById(id));
            selectedList2.add(serialWithDatesService.findByName(serialRepository.findById(id).getName()));
        }

        for(Serial ser : selectedList1) {
            serialRepository.delete(ser);
        }
        for(SerialWithDates swd : selectedList2) {
            serialWithDatesService.delete(swd.getId());
        }
    }

    @Transactional
    @Override
    public Serial add (Serial addForm) {

        if (serialRepository.findByName(addForm.getName()).isEmpty()) {
            Serial serial = new Serial();
            SerialWithDates swd = new SerialWithDates();

            serial.setName(addForm.getName());
            swd.setName(addForm.getName());
            LocalDateTime time = LocalDateTime.now();
            swd.setDate(time);
            swd.setDateString(time);
            return serial;

        }
        else {
            return null;
        }
    }
}
