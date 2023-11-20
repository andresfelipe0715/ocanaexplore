package com.proyecto_turismo_ufpso.turismo.plan.controller;

import com.proyecto_turismo_ufpso.turismo.plan.dto.PlanDto;
import com.proyecto_turismo_ufpso.turismo.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping("/all")
    public ResponseEntity<List<PlanDto>> getAllPlan() {
        List<PlanDto> plans = planService.getAllPlan();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanDto> findById(@PathVariable UUID planId) {
        Optional<PlanDto> plan = planService.getPlanId(planId);
        return plan.map(planDto -> new ResponseEntity<>(planDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("save")
    public ResponseEntity<PlanDto> savePlan(@RequestBody PlanDto planDto) {
        PlanDto createdPlan = planService.savePlan(planDto);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }


    @PutMapping("/{planId}")
    public ResponseEntity<PlanDto> updatePlan(@PathVariable UUID planId, @RequestBody PlanDto planDto) {
        PlanDto updatedPlan = planService.updatePlan(planId, planDto);
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable UUID planId) {
        planService.deletePlan(planId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<PlanDto> getPlanByUserId(@PathVariable("userId") UUID userId) {
        // Llama al servicio para buscar el plan por el ID de usuario
        PlanDto plan = planService.getPlanByUser(userId);

        if (plan != null) {
            return new ResponseEntity<>(plan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
