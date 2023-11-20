package com.proyecto_turismo_ufpso.turismo.config;

import com.proyecto_turismo_ufpso.turismo.planDetail.dto.PlanDetailDto;
import com.proyecto_turismo_ufpso.turismo.offerDetail.dto.OfferDetailDto;
import com.proyecto_turismo_ufpso.turismo.planDetail.entity.PlanDetail;
import com.proyecto_turismo_ufpso.turismo.offerDetail.entity.OfferDetail;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
      ModelMapper modelMapper = new ModelMapper();

        // Configura el mapeo personalizado entre CartProduct y CartProductDto
        modelMapper.addMappings(new PropertyMap<PlanDetail, PlanDetailDto>() {
            @Override
            protected void configure() {
                map().setServiceName(source.getService().getServiceName());
                map().setServiceImg(source.getService().getServiceImg());
            }
        });


        return modelMapper;
    }



}

 */




@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configura el mapeo personalizado entre OfferDetail y OfferDetailDto
        modelMapper.addMappings(new PropertyMap<OfferDetail, OfferDetailDto>() {
            @Override
            protected void configure() {
                // Map the serviceName directly from the service in OfferDetail
                map().setServiceName(source.getService().getServiceName());
            }
        });

        // Add a separate configuration for PlanDetailDto (only if you have a PlanDetail class)
        modelMapper.addMappings(new PropertyMap<PlanDetail, PlanDetailDto>() {
            @Override
            protected void configure() {
                // Map the serviceName directly from the service in PlanDetail
                map().setServiceName(source.getService().getServiceName());
            }
        });

        return modelMapper;
    }



}






