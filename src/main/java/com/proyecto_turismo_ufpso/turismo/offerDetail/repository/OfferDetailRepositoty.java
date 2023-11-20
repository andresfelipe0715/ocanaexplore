package com.proyecto_turismo_ufpso.turismo.offerDetail.repository;

import com.proyecto_turismo_ufpso.turismo.offerDetail.entity.OfferDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OfferDetailRepositoty extends JpaRepository<OfferDetail, UUID> {

    List<OfferDetail> getByOfferId (UUID offerId);

    @Query(value = "select sum(od.subtotal) from turismo.offer_detail od where od.fk_offer_id = :offerId", nativeQuery = true)
    Double sumSubtotalByOfferId(UUID offerId);
}
