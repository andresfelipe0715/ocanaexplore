package com.proyecto_turismo_ufpso.turismo.profile.repository;

import com.proyecto_turismo_ufpso.turismo.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByProfileName(String profileName);

    boolean existsByProfileName(String profileName);



}
