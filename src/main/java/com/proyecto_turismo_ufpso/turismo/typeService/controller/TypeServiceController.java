package com.proyecto_turismo_ufpso.turismo.typeService.controller;


import com.proyecto_turismo_ufpso.turismo.typeService.dto.TypeServiceDto;
import com.proyecto_turismo_ufpso.turismo.typeService.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/typeService")
public class TypeServiceController {

    @Autowired
    private TypeServiceService typeServiceService;

    @GetMapping("/all")
    public ResponseEntity<List<TypeServiceDto>> getAll(){
        return new ResponseEntity<>(typeServiceService.getAllTypesServices(), HttpStatus.OK);
    }

    @GetMapping("/{typeId}")
    public ResponseEntity<TypeServiceDto> findById(@PathVariable UUID typeId){
        return typeServiceService.getTypeId(typeId).map(typeServiceDto ->
                new ResponseEntity<>(typeServiceDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<TypeServiceDto> saveType(@RequestBody TypeServiceDto typeServiceDto){
        return new ResponseEntity<>(typeServiceService.saveType(typeServiceDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{typeId}")
    public ResponseEntity<TypeServiceDto> updateTypeService(@RequestBody TypeServiceDto typeServiceDto, @PathVariable("typeId") UUID typeId){
        return new ResponseEntity<>(typeServiceService.updateType(typeId,typeServiceDto), HttpStatus.OK);
    }
}
