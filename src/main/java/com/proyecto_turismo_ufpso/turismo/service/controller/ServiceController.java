package com.proyecto_turismo_ufpso.turismo.service.controller;

import com.proyecto_turismo_ufpso.turismo.service.dto.ServiceDto;
import com.proyecto_turismo_ufpso.turismo.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/all")
    public ResponseEntity<List<ServiceDto>> getAll(){
        return new ResponseEntity<>(serviceService.getAllService(), HttpStatus.OK);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceDto> findById(@PathVariable UUID serviceId){
        return serviceService.getServiceId(serviceId).map(serviceDto -> new ResponseEntity<>(serviceDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceDto> saveService(@RequestBody ServiceDto serviceDto){
        return new ResponseEntity<>(serviceService.saveService(serviceDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{serviceId}") //actualizar un producto
    public ResponseEntity<ServiceDto> updateService(@RequestBody ServiceDto serviceDto, @PathVariable("serviceId") UUID serviceId) {
        return new ResponseEntity<>(serviceService.updateService(serviceId, serviceDto),HttpStatus.OK);

    }
    @DeleteMapping("/delete/{serviceId}")
    public ResponseEntity deleteService(@PathVariable UUID serviceId){
        if (serviceService.deleteService(serviceId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/typeService/{typeId}")
    public  ResponseEntity<List<ServiceDto>> getServiceByType(@PathVariable("typeId")UUID typeId){
        List<ServiceDto> services = serviceService.getServiceByType(typeId);
        if (!services.isEmpty()){
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/typeServiceName/{typeName}")
    public ResponseEntity<List<ServiceDto>> getServicesByTypeName (@PathVariable("typeName") String typeName){
        List<ServiceDto> services = serviceService.getServiceByTypeName(typeName);
        if (!services.isEmpty()){
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/serviceName/{serviceName}")
    public ResponseEntity<ServiceDto> findByName(@PathVariable String serviceName){
        return serviceService.getServiceName(serviceName).map(serviceDto -> new ResponseEntity<>(serviceDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
