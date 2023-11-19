package com.proyecto_turismo_ufpso.turismo.offerDetail.service;

import com.proyecto_turismo_ufpso.turismo.offerDetail.dto.OfferDetailDto;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferDetailService {
    List<OfferDetailDto> getAllOfferDetails();

    Optional<OfferDetailDto> getOfferDetailId(UUID offerDetailId);

    OfferDetailDto saveOfferDetail (OfferDetailDto offerDetailDto);

    Boolean deleteOfferDetail (UUID offerDetailId);

    List<OfferDetailDto> getOfferDetailByOffer(UUID offerId);

    OfferDetailDto updateOfferDetail(UUID offerDetailId, OfferDetailDto offerDetailDto);
}
