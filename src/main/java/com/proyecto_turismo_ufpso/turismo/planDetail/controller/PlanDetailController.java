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

    /*@DeleteMapping("/delete/{cartProductId}")
    public ResponseEntity deleteCartProduct (@PathVariable UUID cartProductId){
        if(cartProductService.deleteCartProduct(cartProductId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity <List<CartProductDto>> getCartProductByCart (@PathVariable("cartId") UUID cartId){
        List<CartProductDto> cartProducts = cartProductService.getCartProductByCart(cartId);
        if (!cartProducts.isEmpty()){
            return new ResponseEntity<>(cartProducts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{cartProductId}") //actualizar
    public  ResponseEntity <CartProductDto> updateCartProduct (@RequestBody CartProductDto cartProductDto, @PathVariable("cartProductId") UUID cartProductId){
        return new ResponseEntity<>(cartProductService.updateCartProduct(cartProductId, cartProductDto), HttpStatus.OK);
    }*/

}
