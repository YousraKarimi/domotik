package episen.Bike.services;


import back.models.BikeModel.Demand;
import episen.Bike.repositories.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandService {
    @Autowired
    private DemandRepository demandRepository;

    public Demand addDemand(Demand demand) {
        return demandRepository.save(demand);
    }

    public List<Demand> getDemands(){ return  demandRepository.findAll();}

}
