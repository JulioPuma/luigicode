package com.luigicode.userservice.service;

import com.luigicode.userservice.entity.User;
import com.luigicode.userservice.feignclients.BikeFeignClient;
import com.luigicode.userservice.feignclients.CarFeignClient;
import com.luigicode.userservice.model.Bike;
import com.luigicode.userservice.model.Car;
import com.luigicode.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public List<Object> getCars(int userId){
        String URL = "http://localhost:8002/cars/byuser/"+userId;
        return restTemplate.getForObject(URL, List.class);
    }

    public List<Bike> getBikes(int userId){
        String URL = "http://localhost:8003/bikes/byuser/"+userId;
        return restTemplate.getForObject(URL, List.class);
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        return bikeFeignClient.save(bike);
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            result.put("Mensaje", "No existe el usuario");
            return result;
        }
        result.put("User",user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars == null || cars.isEmpty())
            result.put("Cars","Ese user no tiene coches");
        else
            result.put("Cars", cars);

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes == null || bikes.isEmpty())
            result.put("Bikes","Ese user no tiene motos");
        else
            result.put("Bikes", bikes);

        return result;
    }
}
