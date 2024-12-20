package episen.Bike.controllers;


import back.models.BikeModel.Bike;
import episen.Bike.services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bike/bike")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping("/{id}")
    public ResponseEntity<Bike> findById(@PathVariable Long id){

        return new ResponseEntity<>(bikeService.findByIdBike(id), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<Bike>> findAllBike(){
        return new ResponseEntity<>(bikeService.findAllBike(), HttpStatus.OK);
    }


    @GetMapping("/available-in-station/{stationId}")
    public ResponseEntity<Integer> getAvailableBikesInStation(@PathVariable Long stationId) {
        List<Bike> availableBikes = bikeService.getAvailableBikesInStation(stationId);
        return ResponseEntity.ok(availableBikes.size());
    }
    @GetMapping("/numberOfBikes")
    public ResponseEntity<List<Map.Entry<String, Integer>>> getNumberOfBikes() {
        List<Map.Entry<String, Integer>> result = bikeService.getNumberOfBikes();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//    @GetMapping("/updateBikeData")
//    public ResponseEntity<String> updateBikeData() {
//        bikeService.updateBikeDataRegularly();
//        return new ResponseEntity<>("Bike data updated successfully", HttpStatus.OK);
//    }




}
