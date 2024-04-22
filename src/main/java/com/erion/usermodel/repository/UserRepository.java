package com.erion.usermodel.repository;

import com.erion.usermodel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.email= :email and u.userName= :username and u.password= :password")
    List<User> checkDuplicates(@Param("email") String email, @Param("username") String username, @Param("password") String password);
}
