package com.drvr1.applogic.Repositories;

import com.drvr1.applogic.Entities.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User2, Long> {
    User2 findByEmail(String email);
}
