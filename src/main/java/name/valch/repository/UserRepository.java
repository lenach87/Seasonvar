package name.valch.repository;


import name.valch.entity.User;
import name.valch.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
    User findByProfile (UserProfile profile);

//    List<User> findByRole (String role);

//    List<User> findByDevices(Device device);
}
