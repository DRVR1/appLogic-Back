package com.drvr1.applogic.Services;
import com.drvr1.applogic.Entities.User2;
import com.drvr1.applogic.Entities.*;
import com.drvr1.applogic.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityService {

    @Autowired
    private final UserRepository userRepository;


    public Optional<User2> findByEmail(String email) {
        User2 user = userRepository.findByEmail(email);
        if (user != null){
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
