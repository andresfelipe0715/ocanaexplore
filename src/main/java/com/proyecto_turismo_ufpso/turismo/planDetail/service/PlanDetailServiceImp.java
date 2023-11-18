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
    public PlanDetailDto savePlanDetail(PlanDetailDto planDetailDto, ServiceDto serviceDto) {
        // Calcular subtotal
        double subtotal = 0;

        if ("Hoteleria".equals(serviceDto.getTypeName())) {
            double room = serviceDto.getRoom() != null ? serviceDto.getRoom() : 0;
            double doubleRoom = serviceDto.getDoubleRoom() != null ? serviceDto.getDoubleRoom() : 0;
            double nightAmount = serviceDto.getNightAmount() != null ? serviceDto.getNightAmount() : 0;
            double roomAmount = planDetailDto.getRoomAmount() != null ? planDetailDto.getRoomAmount() : 0;
            double doubleRoomAmount = planDetailDto.getDoubleRoomAmount() != null ? planDetailDto.getDoubleRoomAmount() : 0;
            subtotal = (room * nightAmount * roomAmount) + (doubleRoom * nightAmount * doubleRoomAmount);
        } else if ("Restaurante".equals(serviceDto.getTypeName())) {
            double foodPrice = serviceDto.getFoodPrice() != null ? serviceDto.getFoodPrice() : 0;
            double foodAmount = planDetailDto.getFoodAmount() != null ? planDetailDto.getFoodAmount() : 0;
            double priceTrans = serviceDto.getPriceTrans() != null ? serviceDto.getPriceTrans() : 0;
            double tripAmount = planDetailDto.getTripAmount() != null ? planDetailDto.getTripAmount() : 0;
            subtotal = (foodPrice * foodAmount) + (priceTrans * tripAmount);
        } else if ("Sitio turistico".equals(serviceDto.getTypeName())) {
            double entranceFee = serviceDto.getEntranceFee() != null ? serviceDto.getEntranceFee() : 0;
            double personalGuide = serviceDto.getPersonalGuide() != null ? serviceDto.getPersonalGuide() : 0;
            double priceTrans = serviceDto.getPriceTrans() != null ? serviceDto.getPriceTrans() : 0;
            double tripAmount = planDetailDto.getTripAmount() != null ? planDetailDto.getTripAmount() : 0;
            subtotal = entranceFee + personalGuide + (priceTrans * tripAmount);
        }

        // Establecer el subtotal en el PlanDetailDto
        planDetailDto.setSubtotal(subtotal);

        // Ahora puedes guardar el PlanDetailDto en la base de datos o realizar otras operaciones necesarias.

        // Ejemplo de guardado en la base de datos
        PlanDetail planDetailEntity = modelMapper.map(planDetailDto, PlanDetail.class);
        PlanDetail savedPlanDetailEntity = planDetailRepository.save(planDetailEntity);

        // Mapear y devolver el resultado
        return modelMapper.map(savedPlanDetailEntity, PlanDetailDto.class);
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
