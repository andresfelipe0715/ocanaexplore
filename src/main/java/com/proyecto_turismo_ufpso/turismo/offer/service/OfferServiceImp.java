package com.proyecto_turismo_ufpso.turismo.offer.service;

import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.InternalServerException;
import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.offer.dto.OfferDto;
import com.proyecto_turismo_ufpso.turismo.offer.entity.Offer;
import com.proyecto_turismo_ufpso.turismo.offer.repository.OfferRepositoty;
import com.proyecto_turismo_ufpso.turismo.offerDetail.repository.OfferDetailRepositoty;
import com.proyecto_turismo_ufpso.turismo.plan.dto.PlanDto;
import com.proyecto_turismo_ufpso.turismo.plan.entity.Plan;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImp implements OfferService{
    @Autowired
    private OfferRepositoty offerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OfferDetailRepositoty offerDetailRepository;

    @Override
    public List<OfferDto> getAllOffer() {
        return offerRepository.findAll().stream().map(offer ->
                        modelMapper.map(offer, OfferDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OfferDto> getOfferId(UUID offerId) {
        return Optional.ofNullable(offerRepository.findById(offerId).map(offer ->
                modelMapper.map(offer, OfferDto.class)).orElseThrow(() ->
                new MessageGeneric("No se encontro el offer que esta solicitando", "404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public OfferDto saveOffer(OfferDto offerDto) {
        try{
            Offer offer = modelMapper.map(offerDto, Offer.class);

            offer.setTotal(0.0);

            Offer savedOffer = offerRepository.save(offer);
            return modelMapper.map(savedOffer, OfferDto.class);
        } catch (Exception ex){
            ex.printStackTrace();
            throw new InternalServerException("Error al intentar guardar el offer", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public OfferDto updateOffer(UUID offerId, OfferDto offerDto) {
        Offer offer =  offerRepository.findById(offerId)
                .orElseThrow(() -> new MessageGeneric("Plan no encontrado", "404", HttpStatus.NOT_FOUND));
        Double total = offerDetailRepository.sumSubtotalByOfferId(offerId);

        // Actualizar el subtotal del cart
        offer.setTotal(total);

        // Guardar el cart actualizado
        offer = offerRepository.save(offer);

        return modelMapper.map(offer, OfferDto.class);
    }


    /*@Override
public OfferDto updateOffer(UUID offerId, OfferDto offerDto) {
    Offer offer = offerRepository.findById(offerId)
            .orElseThrow(() -> new MessageGeneric("Plan no encontrado", "404", HttpStatus.NOT_FOUND));
    Double total = offerDetailRepository.sumSubtotalByOfferId(offerId);

    // Aplicar descuento
    Double percentage = offerDto.getPercentage();
    Double discount = offerDto.getDiscount();

    if (percentage != null && discount != null) {
        // Calcular el descuento
        Double discountAmount = (percentage / 100) * total;
        total -= discountAmount;

        // Almacenar el total después del descuento en totalDiscount
        offer.setTotalDiscount(total);
    }

    // Actualizar el subtotal del cart
    offer.setTotal(total);

    // Guardar el cart actualizado
    offer = offerRepository.save(offer);

    return modelMapper.map(offer, OfferDto.class);
}


     */
    @Override
    public OfferDto getOfferByUser(UUID userId) {
        Optional<Offer> offer = offerRepository.findByUserId(userId);

        if (offer.isPresent()) {
            return modelMapper.map(offer.get(), OfferDto.class);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteOffer(UUID offerId) {
        if (offerRepository.findById(offerId).isPresent()){
            offerRepository.deleteById(offerId);
            return true;
        }
        return false;
    }
}
