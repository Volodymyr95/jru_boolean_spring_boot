package com.codegym.app.repository;

import com.codegym.app.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByFirstNameStartingWith(String prefix);// select * from User where firstName like

    List<User> findAllByAgeGreaterThanEqual(Integer age); //select * from User where age <= value

    List<User> findAllByFirstNameOrLastName(String firstName, String lastName);

    @Query(value = "select u from User u where u.id =?1")
    User getMyUserById(Long id);

    @Modifying
    @Query("update User u set u.email = :email where u.id = :id")
    void updateEmail(@Param(value = "id") Long id, @Param(value = "email") String email);

}
