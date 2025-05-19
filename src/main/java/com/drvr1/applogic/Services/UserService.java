package com.drvr1.applogic.Services;

import com.drvr1.applogic.Entities.User2;
import com.drvr1.applogic.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User2 createUser(User2 user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        User2 user1 = User2.builder().email(user.getEmail()).password(encodedPass).role(user.getRole()).build();
        return adminRepository.save(user1);
    }

}
