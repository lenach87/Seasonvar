package name.valch.service;

import name.valch.entity.Serial;

import java.util.List;


public interface SerialService {

    Serial save(Serial serial);

    List<Serial> findAll();

    Serial findOne(Long id);

    List<Serial> findByName(String name);

    void delete(Long id);

    Serial update(Serial serial);
    void deleteMultiple (Long[] ids);
    public Serial add (Serial addForm);
}
