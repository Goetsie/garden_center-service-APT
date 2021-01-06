package fact.it.garden_centerservice.controller;


import fact.it.garden_centerservice.model.GardenCenter;
import fact.it.garden_centerservice.repository.GardenCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class GardenCenterController {

    @Autowired
    private GardenCenterRepository gardenCenterRepository;

    @PostConstruct
    public void fillDB(){
        if(gardenCenterRepository.count()==0){
            gardenCenterRepository.save(new GardenCenter("Tuincentrum Geel","Geelstraat 2, Geel"));
            gardenCenterRepository.save(new GardenCenter("Plantenhuis","Plantstraat 16, Aarschot"));
            gardenCenterRepository.save(new GardenCenter( "TuinMagazijn","Tuinstraat 34, Brussel"));
        }
        //System.out.println("Reviews test: " + gardenCenterRepository.getAllBy());
    }
    @GetMapping("/reviews/user/{address}")
    public List<GardenCenter> getGardenCentersByLocation(@PathVariable String address){
        return gardenCenterRepository.findAllByAdresLike(address);
    }
}
