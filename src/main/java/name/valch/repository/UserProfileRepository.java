package name.valch.repository;


import name.valch.entity.User;
import name.valch.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUserName(User user);

//    List<User> findByRole (String role);

//    List<User> findByDevices(Device device);
}
