package com.veliqo.codeChallenge.user;

import com.veliqo.codeChallenge.user.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User u SET u.status = :status WHERE u.username = :email")
    int updateStatusByEmail(@Param("status") Status status, @Param("email") String email);

    Optional<User> findByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User u SET u.password = :newPassword WHERE u.username = :email")
    int updatePasswordByEmail(@Param("email") String email,@Param("newPassword") String newPassword);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User u SET u.name = :name WHERE u.username = :email")
    int updateName(@Param("name") String name, @Param("email") String email);

    Optional<User> findByName(String username);

    Long countAllByRoles(String role_admin);
}
