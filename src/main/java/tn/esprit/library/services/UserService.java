package tn.esprit.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.library.entities.User;
import tn.esprit.library.repository.IUserRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService implements IUserService{
    IUserRepository userRepository;
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User checkProfile(String mail, String pass) {
        List<User> allUsers=userRepository.findAll();
        for (User u:
                allUsers) {
            if (u.getMail().equals(mail) && u.getPassword().equals(pass))
                return u;
        }
        return null;
    }
}
