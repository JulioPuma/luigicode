package com.luigicode.userservice.controller;

import com.luigicode.userservice.entity.User;
import com.luigicode.userservice.model.Bike;
import com.luigicode.userservice.model.Car;
import com.luigicode.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAll(){
        List<User> userList = userService.getAll();
        if(userList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(
            @PathVariable(name = "id") int id){
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> save(
            @RequestBody User user){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Object>> getCars(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Object> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("save-car/{userId}")
    public ResponseEntity<Car> saveCar(
            @PathVariable("userId") int userId,
            @RequestBody Car car) {
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Car newCar = userService.saveCar(userId, car);
        return ResponseEntity.ok(newCar);
    }

    @PostMapping("save-bike/{userId}")
    public ResponseEntity<Bike> saveBike(
            @PathVariable("userId") int userId,
            @RequestBody Bike bike) {
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Bike newBike = userService.saveBike(userId, bike);
        return ResponseEntity.ok(newBike);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String, Object> map = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(map);
    }
}
