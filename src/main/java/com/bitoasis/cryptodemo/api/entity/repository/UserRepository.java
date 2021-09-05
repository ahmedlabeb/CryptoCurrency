package com.bitoasis.cryptodemo.api.entity.repository;


import com.bitoasis.cryptodemo.api.entity.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByName(String name);
}
