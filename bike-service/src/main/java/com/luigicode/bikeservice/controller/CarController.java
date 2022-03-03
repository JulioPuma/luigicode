package com.luigicode.bikeservice.controller;

import com.luigicode.bikeservice.entity.Bike;
import com.luigicode.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class CarController {

    @Autowired
    private BikeService bikeService;

    @GetMapping("")
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> userList = bikeService.getAll();
        if (userList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(
            @PathVariable(name = "id") int id) {
        Bike bike = bikeService.getCarById(id);
        if (bike == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bike);
    }

    @PostMapping("")
    public ResponseEntity<Bike> save(
            @RequestBody Bike bike) {
        Bike userNew = bikeService.save(bike);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> userList = bikeService.byUserId(userId);
        if (userList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userList);
    }
}
