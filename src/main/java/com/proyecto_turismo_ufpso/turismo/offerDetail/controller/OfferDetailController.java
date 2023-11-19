package com.proyecto_turismo_ufpso.turismo.offerDetail.controller;

import com.proyecto_turismo_ufpso.turismo.offerDetail.dto.OfferDetailDto;
import com.proyecto_turismo_ufpso.turismo.offerDetail.service.OfferDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/offerDetail")
public class OfferDetailController {
    @Autowired
    private OfferDetailService offerDetailService;



    @GetMapping("/all")
    public ResponseEntity<List<OfferDetailDto>> getAll(){
        return new ResponseEntity<>(offerDetailService.getAllOfferDetails(), HttpStatus.OK);
    }

    @GetMapping("/{offerDetailId}")
    public ResponseEntity<OfferDetailDto> finById(@PathVariable UUID offerDetailId){
        return offerDetailService.getOfferDetailId(offerDetailId).map(offerDetailDto ->
                        new ResponseEntity<>(offerDetailDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity <OfferDetailDto> saveOfferDetail(@RequestBody OfferDetailDto offerDetailDto){
        return new ResponseEntity<>(offerDetailService.saveOfferDetail(offerDetailDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{offerDetailId}")
    public ResponseEntity deleteOfferDetail (@PathVariable UUID offerDetailId){
        if(offerDetailService.deleteOfferDetail(offerDetailId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/offer/{offerId}")
    public ResponseEntity <List<OfferDetailDto>> getOfferDetailByOffer (@PathVariable("offerId") UUID offerId){
        List<OfferDetailDto> offerDetailDtos = offerDetailService.getOfferDetailByOffer(offerId);
        if (!offerDetailDtos.isEmpty()){
            return new ResponseEntity<>(offerDetailDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{offerDetailId}") //actualizar
    public  ResponseEntity <OfferDetailDto> updateCartProduct (@RequestBody OfferDetailDto offerDetailDto, @PathVariable("offerDetailId") UUID offerDetailId){
        return new ResponseEntity<>(offerDetailService.updateOfferDetail(offerDetailId, offerDetailDto), HttpStatus.OK);
    }
}
