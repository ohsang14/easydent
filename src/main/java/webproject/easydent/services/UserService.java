package webproject.easydent.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.User;
import webproject.easydent.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
