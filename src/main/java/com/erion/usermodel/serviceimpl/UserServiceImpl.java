package com.erion.usermodel.serviceimpl;

import com.erion.usermodel.entity.User;
import com.erion.usermodel.repository.UserRepository;
import com.erion.usermodel.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id does not exist"));
    }

    @Override
    public ResponseEntity save(User user) {
        List<User> users = userRepository.checkDuplicates(user.getEmail(), user.getUserName(), user.getPassword());
        if (users.isEmpty()){
            userRepository.save(user);
            return ResponseEntity.ok().body("User registered");
        }else {
            return ResponseEntity.ok().body("User exists");
        }
    }


    @Override
    public ResponseEntity update(User user, Integer id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id: "+id+" does not exist"));
        if (foundUser != null) {
            foundUser.setEmail(user.getEmail());
            foundUser.setUserName(user.getUserName());
            foundUser.setPassword(user.getPassword());
            return save(foundUser);
        }
        return ResponseEntity.ok().body("User not found");
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
