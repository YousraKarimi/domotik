package episen.back.Config.services;


import episen.back.Config.models.User;
import episen.back.Config.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User authenticate(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password)
                .orElse(null);
    }

}
