package webproject.easydent.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.User;
import webproject.easydent.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public void updateUser(User user) {
        User updateUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        updateUser.setName(user.getName());
        updateUser.setPhoneNumber(user.getPhoneNumber());
        updateUser.setBirthDay(user.getBirthDay());
        updateUser.setAddress(user.getAddress());

        userRepository.save(updateUser);
    }
}