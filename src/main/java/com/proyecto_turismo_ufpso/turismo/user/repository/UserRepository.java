package com.proyecto_turismo_ufpso.turismo.user.repository;

import com.proyecto_turismo_ufpso.turismo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserName(String userName);

    Boolean existsByUserName (String userName);

    @Modifying
    @Transactional
    @Query("delete from User where userId = :userId")
    boolean deleteUserByUserId(UUID userId);

}
