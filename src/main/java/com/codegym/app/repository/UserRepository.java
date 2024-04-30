package com.codegym.app.repository;

import com.codegym.app.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Modifying
    @Query("update User u set u.email = :email where u.id = :id")
    void updateEmail(@Param(value = "id") Long id, @Param(value = "email") String email);

    List<User> findByFirstNameStartingWith(String prefix);

    List<User> findAllByAgeGreaterThanEqual(Integer age);

    List<User> findAllByFirstNameOrLastName(String firstName, String lastName);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
