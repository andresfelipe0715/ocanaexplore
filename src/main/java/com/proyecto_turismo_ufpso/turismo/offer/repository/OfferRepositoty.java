package com.proyecto_turismo_ufpso.turismo.offer.repository;


import com.proyecto_turismo_ufpso.turismo.offer.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OfferRepositoty extends JpaRepository<Offer, UUID> {

    Optional<Offer> findByUserId(UUID userId);
}
