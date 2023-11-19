package com.proyecto_turismo_ufpso.turismo.offer.controller;

import com.proyecto_turismo_ufpso.turismo.offer.dto.OfferDto;
import com.proyecto_turismo_ufpso.turismo.offer.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping("/all")
    public ResponseEntity<List<OfferDto>> getAllOffer() {
        List<OfferDto> offers = offerService.getAllOffer();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferDto> findById(@PathVariable UUID offerId) {
        Optional<OfferDto> offer = offerService.getOfferId(offerId);
        return offer.map(offerDto -> new ResponseEntity<>(offerDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("save")
    public ResponseEntity<OfferDto> saveOffer(@RequestBody OfferDto offerDto) {
        OfferDto createdOffer = offerService.saveOffer(offerDto);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }


    @PutMapping("/{offerId}")
    public ResponseEntity<OfferDto> updateOffer(@PathVariable UUID offerId, @RequestBody OfferDto offerDto) {
        OfferDto updatedOffer = offerService.updateOffer(offerId, offerDto);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable UUID offerId) {
        offerService.deleteOffer(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<OfferDto> getOfferByUserId(@PathVariable("userId") UUID userId) {
        // Llama al servicio para buscar el offer por el ID de usuario
        OfferDto offer = offerService.getOfferByUser(userId);

        if (offer != null) {
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
