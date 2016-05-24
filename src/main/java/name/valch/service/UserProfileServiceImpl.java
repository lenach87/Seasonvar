package name.valch.service;

import name.valch.entity.User;
import name.valch.entity.UserProfile;
import name.valch.repository.UserProfileRepository;
import name.valch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public UserProfile findByUserName(User login) {
        return userProfileRepository.findByUserName(login);
    }


    @Override
    @Transactional
    public Iterable<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    @Transactional
    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.saveAndFlush(userProfile);
    }

    @Override
    public UserProfile findOne (long id) {
        return userProfileRepository.findOne(id);
    }

    @Override
    @Transactional
    public void deleteOne (long id) { userProfileRepository.delete(id); }


}
