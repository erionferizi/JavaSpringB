package com.erion.usermodel.service;

import com.erion.usermodel.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public List<User> getAll();

    public User getById(Integer id);

    public ResponseEntity save(User user);

    public ResponseEntity update(User user, Integer id);

    public void delete(Integer id);
}
