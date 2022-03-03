package com.luigicode.userservice.feignclients;

import com.luigicode.userservice.model.Bike;
import com.luigicode.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "bike-service",
        url = "http://localhost:8003",
        path = "/bikes")
public interface BikeFeignClient {
    @PostMapping("")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}
