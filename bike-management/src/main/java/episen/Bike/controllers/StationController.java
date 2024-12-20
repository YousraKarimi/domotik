package episen.Bike.controllers;


import back.models.BikeModel.Station;
import episen.Bike.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bike/station")
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping("/stations")
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }



}
