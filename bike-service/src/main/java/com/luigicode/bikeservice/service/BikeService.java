package com.luigicode.bikeservice.service;

import com.luigicode.bikeservice.entity.Bike;
import com.luigicode.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getCarById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike) {
        return bikeRepository.save(bike);
    }

    public List<Bike> byUserId(int userId) {
        return bikeRepository.findByUserId(userId);
    }
}
