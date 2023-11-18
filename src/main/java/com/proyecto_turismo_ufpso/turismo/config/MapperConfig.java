package com.proyecto_turismo_ufpso.turismo.config;

import com.proyecto_turismo_ufpso.turismo.planDetail.dto.PlanDetailDto;
import com.proyecto_turismo_ufpso.turismo.planDetail.entity.PlanDetail;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
