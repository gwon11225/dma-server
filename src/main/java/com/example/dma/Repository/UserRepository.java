package com.example.dma.Repository;

import com.example.dma.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "INSERT INTO user (name, email, password) VALUES (:name, :email, :password)", nativeQuery = true)
    void insertUserData(@Param("name") String name, @Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT COUNT(*) FROM user WHERE email = :email AND password = :password", nativeQuery = true)
    int isLogin(@Param("email") String email, @Param("password") String password);

    User findUserByEmail(String email);

    void deleteById(Long id);
}
