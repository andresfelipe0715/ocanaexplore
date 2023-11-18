package com.proyecto_turismo_ufpso.turismo.plan.service;

import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.InternalServerException;
import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.plan.dto.PlanDto;
import com.proyecto_turismo_ufpso.turismo.plan.entity.Plan;
import com.proyecto_turismo_ufpso.turismo.plan.repository.PlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlanServiceImp implements PlanService{

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<PlanDto> getAllPlan() {
        return planRepository.findAll().stream().map(plan ->
                modelMapper.map(plan, PlanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlanDto> getPlanId(UUID planId) {
        return Optional.ofNullable(planRepository.findById(planId).map(plan ->
                modelMapper.map(plan,PlanDto.class)).orElseThrow(() ->
                new MessageGeneric("No se encontro el plan que esta solicitando", "404", HttpStatus.NOT_FOUND)));

    }
    @Override
    public PlanDto savePlan(PlanDto planDto) {
       try{
           Plan plan = modelMapper.map(planDto, Plan.class);

           plan.setTotal(0.0);

           Plan savedPlan = planRepository.save(plan);
           return modelMapper.map(savedPlan, PlanDto.class);
       } catch (Exception ex){
           throw new InternalServerException("Error al intentar guardar el plan", "500", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public PlanDto updatePlan(UUID planId, PlanDto planDto) {
        return null;
    }

    @Override
    public Boolean deletePlan(UUID planId) {
        if (planRepository.findById(planId).isPresent()){
            planRepository.deleteById(planId);
            return true;
        }
        return false;
    }

    @Override
    public PlanDto getCartByUser(UUID userId) {
        Optional<Plan> plan = planRepository.findByUserId(userId);

        if (plan.isPresent()) {
            return modelMapper.map(plan.get(), PlanDto.class);
        } else {
            return null;
        }
    }
}
