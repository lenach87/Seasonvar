package name.valch.service;


import name.valch.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {

    User findByLogin(String login);

//    List<User> findByRole (String role);
    Page<User> findAll(Pageable pageable);
    User findOne(long id);
    void deleteOne(long id);
    User update(User user);
    User save(User user);
}
