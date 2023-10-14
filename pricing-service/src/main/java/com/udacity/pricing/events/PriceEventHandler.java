package com.udacity.pricing.events;

import com.udacity.pricing.entity.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/prices")
public class PriceEventHandler {

    @Autowired
    private PriceRepository priceRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Price> getPrice(@PathVariable Long id) {
        Price price = priceRepository.findById(id).orElseGet(() -> {
            Price newPrice = new Price(id, "USD", randomPrice());
            return priceRepository.save(newPrice);
        });

        if(price.getVehicleId() == null) {
            return new ResponseEntity<>(price, HttpStatus.CREATED);
        }
        return ResponseEntity.ok(price);
    }

    private static BigDecimal randomPrice() {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 5))
                .multiply(new BigDecimal("5000")).setScale(2, RoundingMode.HALF_UP);
    }

}


