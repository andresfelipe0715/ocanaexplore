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

        if (planDetailRepository.findById(planDetailId).isPresent()){
            planDetailRepository.deleteById(planDetailId);
            return true;
        }
        return true;
    }

    @Override
    public List<PlanDetailDto> getPlanDetailByPlan(UUID planId) {

        return planDetailRepository.getByPlanId(planId).stream().map(planDetail -> {
            return modelMapper.map(planDetail, PlanDetailDto.class);
        }).collect(Collectors.toList());
    }

    /*@Override
    public PlanDetailDto updatePlanDetail(UUID planDetailId, PlanDetailDto planDetailDto) {
        return planDetailRepository.findById(planDetailId).map(plan -> {

            plan.setDoubleRoomAmount((planDetailDto.getDoubleRoomAmount() != null) ? planDetailDto.getDoubleRoomAmount() : plan.getDoubleRoomAmount());
            plan.setFoodAmount((planDetailDto.getFoodAmount() != null) ? planDetailDto.getFoodAmount() : plan.getFoodAmount());
            plan.setNightAmount((planDetailDto.getNight_amount() != null) ? planDetailDto.getNight_amount() : plan.getNightAmount());
            plan.setRoomAmount((planDetailDto.getRoomAmount() != null) ? planDetailDto.getRoomAmount() : plan.getRoomAmount());
            plan.setTripAmount((planDetailDto.getTripAmount() != null) ? planDetailDto.getTripAmount() : plan.getTripAmount());

            com.proyecto_turismo_ufpso.turismo.service.entity.Service service = serviceRepository.findById(planDetailDto.getServiceId())
                    .orElseThrow(() -> new MessageGeneric("Servicio no encontrado", "404", HttpStatus.NOT_FOUND));

            PlanDetail planDetails = modelMapper.map(planDetailDto, PlanDetail.class);
            planDetails.setService(service);

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
            planDetails.setSubtotal(subtotal);

            // Guardar en el repositorio y devolver el DTO
            planDetails = planDetailRepository.save(planDetails);
            return modelMapper.map(planDetails, PlanDetailDto.class);
        }).orElseThrow(() -> new MessageGeneric("No se encontro el detalle del plan", "404", HttpStatus.NOT_FOUND));
    }*/

    @Override
    public PlanDetailDto updatePlanDetail(UUID planDetailId, PlanDetailDto planDetailDto) {

        // Obtener el PlanDetail de la base de datos
        PlanDetail planDetail = planDetailRepository.findById(planDetailId)
                .orElseThrow(() -> new MessageGeneric("Detalle de plan no encontrado", "404", HttpStatus.NOT_FOUND));

        // Actualizar los atributos del PlanDetail según las condiciones dadas
        planDetail.setDoubleRoomAmount((planDetailDto.getDoubleRoomAmount() != null) ? planDetailDto.getDoubleRoomAmount() : planDetail.getDoubleRoomAmount());
        planDetail.setFoodAmount((planDetailDto.getFoodAmount() != null) ? planDetailDto.getFoodAmount() : planDetail.getFoodAmount());
        planDetail.setNightAmount((planDetailDto.getNight_amount() != null) ? planDetailDto.getNight_amount() : planDetail.getNightAmount());
        planDetail.setRoomAmount((planDetailDto.getRoomAmount() != null) ? planDetailDto.getRoomAmount() : planDetail.getRoomAmount());
        planDetail.setTripAmount((planDetailDto.getTripAmount() != null) ? planDetailDto.getTripAmount() : planDetail.getTripAmount());

        // Actualizar el subtotal según la lógica del servicio savePlanDetail
        com.proyecto_turismo_ufpso.turismo.service.entity.Service service = planDetail.getService();
        double subtotal = 0.0;

        if ("Hoteleria".equals(service.getTypeName())) {
            double roomSubtotal = (planDetail.getRoomAmount() * planDetail.getNightAmount() * service.getRoom())
                    + (planDetail.getDoubleRoomAmount() * planDetail.getNightAmount() * service.getRoom());
            subtotal = roomSubtotal;
        } else if ("Restaurante".equals(service.getTypeName())) {
            double restaurantSubtotal = (service.getFoodPrice() * planDetail.getFoodAmount())
                    + (service.getPriceTrans() * planDetail.getTripAmount());
            subtotal = restaurantSubtotal;
        } else if ("Sitio turistico".equals(service.getTypeName())) {
            double touristSiteSubtotal = service.getEntranceFee() + service.getPersonalGuide()
                    + (service.getPriceTrans() * planDetail.getTripAmount());
            subtotal = touristSiteSubtotal;
        }

        // Establecer el nuevo subtotal en el PlanDetail
        planDetail.setSubtotal(subtotal);

        // Guardar en el repositorio y devolver el DTO actualizado
        planDetail = planDetailRepository.save(planDetail);
        return modelMapper.map(planDetail, PlanDetailDto.class);
    }
}
