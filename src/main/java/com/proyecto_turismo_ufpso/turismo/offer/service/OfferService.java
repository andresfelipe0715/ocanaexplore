package com.proyecto_turismo_ufpso.turismo.offer.service;



import com.proyecto_turismo_ufpso.turismo.offer.dto.OfferDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferService {


    List<OfferDto> getAllOffer();

    Optional<OfferDto> getOfferId(UUID offerId);

    OfferDto saveOffer(OfferDto offerDto);

    OfferDto updateOffer(UUID offerId, OfferDto offerDto);

    Boolean deleteOffer(UUID offerId);

    OfferDto getOfferByUser(UUID userId);

}
