package com.luigicode.carservice.controller;

import com.luigicode.carservice.entity.Car;
import com.luigicode.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("")
    public ResponseEntity<List<Car>> getAll() {
        List<Car> userList = carService.getAll();
        if (userList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(
            @PathVariable(name = "id") int id) {
        Car car = carService.getCarById(id);
        if (car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);
    }

    @PostMapping("")
    public ResponseEntity<Car> save(
            @RequestBody Car car) {
        Car userNew = carService.save(car);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
        List<Car> userList = carService.byUserId(userId);
        if (userList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userList);
    }
}
