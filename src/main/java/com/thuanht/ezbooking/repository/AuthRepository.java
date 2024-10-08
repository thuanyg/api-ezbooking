package com.thuanht.ezbooking.repository;

import com.thuanht.ezbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.id = :id OR u.firebase_uid = :firebase_uid")
    Optional<User> findByEmailOrIdOrFirebaseUid(@Param("email") String email, @Param("id") String id, @Param("firebase_uid") String firebaseUid);

}
