package com.proyecto_turismo_ufpso.turismo.offerDetail.service;


import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.offerDetail.dto.OfferDetailDto;
import com.proyecto_turismo_ufpso.turismo.offerDetail.entity.OfferDetail;
import com.proyecto_turismo_ufpso.turismo.offerDetail.repository.OfferDetailRepositoty;
import com.proyecto_turismo_ufpso.turismo.service.repository.ServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferDetailServiceImp implements OfferDetailService {

    @Autowired
    private OfferDetailRepositoty offerDetailRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper offerDetailModelMapper;

    @Override
    public List<OfferDetailDto> getAllOfferDetails() {
        List<OfferDetail> offerDetails = offerDetailRepository.findAll();

        return offerDetails.stream().map(offerDetail -> {
            OfferDetailDto dto = offerDetailModelMapper.map(offerDetail, OfferDetailDto.class);
            // Añade el nombre y la imagen del producto al DTO
            dto.setServiceName(offerDetail.getService().getServiceName());
            dto.setServiceImg(offerDetail.getService().getServiceImg());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<OfferDetailDto> getOfferDetailId(UUID offerDetailId) {
        return Optional.ofNullable(offerDetailRepository.findById(offerDetailId).map(offerDetail ->
                offerDetailModelMapper.map(offerDetail, OfferDetailDto.class)).orElseThrow(() ->
                new MessageGeneric("No se encontro el detalle del carrito de compras que esta solicitando", "404", HttpStatus.NOT_FOUND)));
    }



    @Override
    public OfferDetailDto saveOfferDetail(OfferDetailDto offerDetailDto) {

        com.proyecto_turismo_ufpso.turismo.service.entity.Service service = serviceRepository.findById(offerDetailDto.getServiceId())
                .orElseThrow(() -> new MessageGeneric("Servicio no encontrado", "404", HttpStatus.NOT_FOUND));

        OfferDetail offerDetail = offerDetailModelMapper.map(offerDetailDto, OfferDetail.class);
        offerDetail.setService(service);

        double subtotal = 0.0;

        // Verificar el tipo de servicio y calcular el subtotal en consecuencia
        if ("Hoteleria".equals(service.getTypeName())) {
            // Tipo de servicio: Hoteleria
            double roomSubtotal = (offerDetailDto.getRoomAmount() != null ? offerDetailDto.getRoomAmount() : 0)
                    * (offerDetailDto.getNight_amount() != null ? offerDetailDto.getNight_amount() : 0)
                    * (service.getRoom() != null ? service.getRoom() : 0)
                    + (offerDetailDto.getDoubleRoomAmount() != null ? offerDetailDto.getDoubleRoomAmount() : 0)
                    * (offerDetailDto.getNight_amount() != null ? offerDetailDto.getNight_amount() : 0)
                    * (offerDetailDto.getDoubleRoomAmount() != null ? offerDetailDto.getDoubleRoomAmount() : 0);
            subtotal = roomSubtotal;
        } else if ("Restaurante".equals(service.getTypeName())) {
            // Tipo de servicio: Restaurante
            double restaurantSubtotal = (service.getFoodPrice() != null ? service.getFoodPrice() : 0)
                    * (offerDetailDto.getFoodAmount() != null ? offerDetailDto.getFoodAmount() : 0)
                    + (service.getPriceTrans() != null ? service.getPriceTrans() : 0)
                    * (offerDetailDto.getTripAmount() != null ? offerDetailDto.getTripAmount() : 0);
            subtotal = restaurantSubtotal;
        } else if ("Sitio turistico".equals(service.getTypeName())) {
            // Tipo de servicio: Sitio Turistico
            double touristSiteSubtotal = (service.getEntranceFee() != null ? service.getEntranceFee() : 0)
                    + (service.getPersonalGuide() != null ? service.getPersonalGuide() : 0)
                    + (service.getPriceTrans() != null ? service.getPriceTrans() : 0)
                    * (offerDetailDto.getTripAmount() != null ? offerDetailDto.getTripAmount() : 0);
            subtotal = touristSiteSubtotal;
        }

        // Establecer el subtotal en el OfferDetail
        offerDetail.setSubtotal(subtotal);

        // Guardar en el repositorio y devolver el DTO
        offerDetail = offerDetailRepository.save(offerDetail);
        return offerDetailModelMapper.map(offerDetail, OfferDetailDto.class);
    }


    @Override
    public Boolean deleteOfferDetail(UUID offerDetailId) {
        if (offerDetailRepository.findById(offerDetailId).isPresent()){
            offerDetailRepository.deleteById(offerDetailId);
            return true;
        }
        return true;
    }

    @Override
    public List<OfferDetailDto> getOfferDetailByOffer(UUID offerId) {
        return offerDetailRepository.getByOfferId(offerId).stream().map(offerDetail -> {
            return offerDetailModelMapper.map(offerDetail, OfferDetailDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public OfferDetailDto updateOfferDetail(UUID offerDetailId, OfferDetailDto offerDetailDto) {
        // Obtener el OfferDetail de la base de datos
        OfferDetail offerDetail = offerDetailRepository.findById(offerDetailId)
                .orElseThrow(() -> new MessageGeneric("Detalle de oferta no encontrado", "404", HttpStatus.NOT_FOUND));

        // Actualizar los atributos del OfferDetail según las condiciones dadas
        offerDetail.setDoubleRoomAmount((offerDetailDto.getDoubleRoomAmount() != null) ? offerDetailDto.getDoubleRoomAmount() : 0);
        offerDetail.setFoodAmount((offerDetailDto.getFoodAmount() != null) ? offerDetailDto.getFoodAmount() : 0);
        offerDetail.setNightAmount((offerDetailDto.getNight_amount() != null) ? offerDetailDto.getNight_amount() : 0);
        offerDetail.setRoomAmount((offerDetailDto.getRoomAmount() != null) ? offerDetailDto.getRoomAmount() : 0);
        offerDetail.setTripAmount((offerDetailDto.getTripAmount() != null) ? offerDetailDto.getTripAmount() : 0);

        // Actualizar el subtotal según la lógica del servicio savePlanDetail
        com.proyecto_turismo_ufpso.turismo.service.entity.Service service = offerDetail.getService();
        double subtotal = 0.0;

        if ("Hoteleria".equals(service.getTypeName())) {
            double roomSubtotal = (offerDetail.getRoomAmount() * offerDetail.getNightAmount() * (service.getRoom() != null ? service.getRoom() : 0))
                    + (offerDetail.getDoubleRoomAmount() * offerDetail.getNightAmount() * (offerDetail.getDoubleRoomAmount() != null ? offerDetail.getDoubleRoomAmount() : 0));
            subtotal = roomSubtotal;
        } else if ("Restaurante".equals(service.getTypeName())) {
            double restaurantSubtotal = ((service.getFoodPrice() != null ? service.getFoodPrice() : 0) * offerDetail.getFoodAmount())
                    + ((service.getPriceTrans() != null ? service.getPriceTrans() : 0) * offerDetail.getTripAmount());
            subtotal = restaurantSubtotal;
        } else if ("Sitio turistico".equals(service.getTypeName())) {
            double touristSiteSubtotal = (service.getEntranceFee() != null ? service.getEntranceFee() : 0) + (service.getPersonalGuide() != null ? service.getPersonalGuide() : 0)
                    + ((service.getPriceTrans() != null ? service.getPriceTrans() : 0) * offerDetail.getTripAmount());
            subtotal = touristSiteSubtotal;
        }

        // Establecer el nuevo subtotal en el OfferDetail
        offerDetail.setSubtotal(subtotal);

        // Guardar en el repositorio y devolver el DTO actualizado
        offerDetail = offerDetailRepository.save(offerDetail);
        return offerDetailModelMapper.map(offerDetail, OfferDetailDto.class);
    }
}
