package com.application.service;

import com.application.data.model.User;
import com.application.data.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public String addUser(User user) {
        try {
            userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Success";
    }
}
