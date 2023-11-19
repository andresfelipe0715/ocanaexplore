package com.proyecto_turismo_ufpso.turismo.planDetail.controller;

import com.proyecto_turismo_ufpso.turismo.planDetail.dto.PlanDetailDto;
import com.proyecto_turismo_ufpso.turismo.planDetail.service.PlanDetailService;
import com.proyecto_turismo_ufpso.turismo.service.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/planDetail")
public class PlanDetailController {

    @Autowired
    private PlanDetailService planDetailService;

    @GetMapping("/all")
    public ResponseEntity<List<PlanDetailDto>> getAll(){
        return new ResponseEntity<>(planDetailService.getAllPlanDetails(), HttpStatus.OK);
    }

    @GetMapping("/{planDetailId}")
    public ResponseEntity<PlanDetailDto> finById(@PathVariable UUID planDetailId){
        return planDetailService.getPlanDetailId(planDetailId).map(planDetailDto ->
                        new ResponseEntity<>(planDetailDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity <PlanDetailDto> savePlanDetail(@RequestBody PlanDetailDto planDetailDto){
        return new ResponseEntity<>(planDetailService.savePlanDetail(planDetailDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{planDetailId}")
    public ResponseEntity deletePlanDetail (@PathVariable UUID planDetailId){
        if(planDetailService.deletePlanDetail(planDetailId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity <List<PlanDetailDto>> getPlanDetailtByPlan (@PathVariable("planId") UUID planId){
        List<PlanDetailDto> planDetailDtos = planDetailService.getPlanDetailByPlan(planId);
        if (!planDetailDtos.isEmpty()){
            return new ResponseEntity<>(planDetailDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{planDetailId}") //actualizar
    public  ResponseEntity <PlanDetailDto> updateCartProduct (@RequestBody PlanDetailDto planDetailDto, @PathVariable("planDetailId") UUID planDetailId){
        return new ResponseEntity<>(planDetailService.updatePlanDetail(planDetailId, planDetailDto), HttpStatus.OK);
    }

}
