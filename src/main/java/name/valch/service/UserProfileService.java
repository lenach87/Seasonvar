package name.valch.service;


import name.valch.entity.User;
import name.valch.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserProfileService {

    UserProfile findByUserName(User login);
//    List<User> findByRole (String role);
    Iterable<UserProfile> findAll();
    UserProfile findOne(long id);
    void deleteOne(long id);
    UserProfile save(UserProfile userProfile);
}
