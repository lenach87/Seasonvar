package name.valch.service;

import name.valch.entity.User;
import name.valch.entity.UserProfile;
import name.valch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileService userProfileService;

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User save(User user) {

        userRepository.saveAndFlush(user);
        UserProfile userProfile = new UserProfile(user);
        userProfileService.save(userProfile);
        return user;
    }

    @Override
    public User findOne (long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public void deleteOne (long id) { userRepository.delete(id); }

    @Override
    @Transactional
    public User update (User user) {
        return userRepository.save(user);
    }
}
