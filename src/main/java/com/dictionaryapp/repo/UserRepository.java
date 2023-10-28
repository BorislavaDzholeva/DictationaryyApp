package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String value);

    Optional<User> findByUsername(String value);


    Optional<User> findByUsernameAndPassword(String username, String password);
}
