package com.proyecto_turismo_ufpso.turismo.planDetail.service;

import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.plan.repository.PlanRepository;
import com.proyecto_turismo_ufpso.turismo.planDetail.dto.PlanDetailDto;
import com.proyecto_turismo_ufpso.turismo.planDetail.entity.PlanDetail;
import com.proyecto_turismo_ufpso.turismo.planDetail.repository.PlanDetailRepository;
import com.proyecto_turismo_ufpso.turismo.service.dto.ServiceDto;
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
public class PlanDetailServiceImp implements PlanDetailService{

    @Autowired
    private PlanDetailRepository planDetailRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PlanDetailDto> getAllPlanDetails() {
        List<PlanDetail> planDetails = planDetailRepository.findAll();

        return planDetails.stream().map(planDetail -> {
            PlanDetailDto dto = modelMapper.map(planDetail, PlanDetailDto.class);
            // Añade el nombre y la imagen del producto al DTO
            dto.setServiceName(planDetail.getService().getServiceName());
            dto.setServiceImg(planDetail.getService().getServiceImg());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<PlanDetailDto> getPlanDetailId(UUID planDetailId) {
        return Optional.ofNullable(planDetailRepository.findById(planDetailId).map(planDetail ->
                modelMapper.map(planDetail, PlanDetailDto.class)).orElseThrow(() ->
                new MessageGeneric("No se encontro el detalle del carrito de compras que esta solicitando", "404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public PlanDetailDto savePlanDetail(PlanDetailDto planDetailDto) {

        com.proyecto_turismo_ufpso.turismo.service.entity.Service service = serviceRepository.findById(planDetailDto.getServiceId())
                .orElseThrow(() -> new MessageGeneric("Servicio no encontrado", "404", HttpStatus.NOT_FOUND));

        PlanDetail planDetail = modelMapper.map(planDetailDto, PlanDetail.class);
        planDetail.setService(service);

        double subtotal = 0.0;

        // Verificar el tipo de servicio y calcular el subtotal en consecuencia
        if ("Hoteleria".equals(service.getTypeName())) {
            // Tipo de servicio: Hoteleria
            double roomSubtotal = (planDetailDto.getRoomAmount() * planDetailDto.getNight_amount() * service.getRoom())
                    + (planDetailDto.getDoubleRoomAmount() * planDetailDto.getNight_amount() * planDetailDto.getDoubleRoomAmount());
            subtotal = roomSubtotal;
        } else if ("Restaurante".equals(service.getTypeName())) {
            // Tipo de servicio: Restaurante
            double restaurantSubtotal = (service.getFoodPrice() * planDetailDto.getFoodAmount())
                    + (service.getPriceTrans() * planDetailDto.getTripAmount());
            subtotal = restaurantSubtotal;
        } else if ("Sitio turistico".equals(service.getTypeName())) {
            // Tipo de servicio: Sitio Turistico
            double touristSiteSubtotal = service.getEntranceFee() + service.getPersonalGuide()
                    + (service.getPriceTrans() * planDetailDto.getTripAmount());
            subtotal = touristSiteSubtotal;
        }

        // Establecer el subtotal en el PlanDetail
        planDetail.setSubtotal(subtotal);

        // Guardar en el repositorio y devolver el DTO
        planDetail = planDetailRepository.save(planDetail);
        return modelMapper.map(planDetail, PlanDetailDto.class);
    }


    @Override
    public Boolean deletePlanDetail(UUID planDetailId) {
        return null;
    }

    @Override
    public List<PlanDetailDto> getPlanDetailByPlan(UUID planId) {
        return null;
    }

    @Override
    public PlanDetailDto updatePlanDetail(UUID planDetailId, PlanDetailDto planDetailDto) {
        return null;
    }
}
