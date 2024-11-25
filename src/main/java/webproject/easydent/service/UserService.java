package webproject.easydent.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webproject.easydent.DataNotFoundException;
import webproject.easydent.entities.User;
import webproject.easydent.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + email));
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

    public User getUser(String email){
        Optional<User> user = this.userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new DataNotFoundException("User is not found");
        }
    }
}