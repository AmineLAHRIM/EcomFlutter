package com.ecomflutter.demo.service.util;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

@Component
public class Helper {
    @Autowired
    private MessageSource messageSource;

    public MappingJacksonValue objectFilter(String filterName, Object data, String... properties) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(properties);

        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);

        MappingJacksonValue mapping = new MappingJacksonValue(data);
        mapping.setFilters(filters);

        return mapping;
    }

    public ResponseEntity<?> response(HttpStatus status, Response response) {
        if (status == HttpStatus.OK) {
            response.setSucces(true);
        }

        return ResponseEntity.status(status).body(
                this.objectFilter("ResponseFilter", response)
        );
    }

    /*public ResponseEntity<?> response(HttpStatus status, boolean succes, Object data) {
        return ResponseEntity.status(status).body(
                this.objectFilter("ResponseFilter", new Response(succes, data), "success", "data", "timestamp")
        );
    }*/
}
