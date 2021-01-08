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
            gardenCenterRepository.save(new GardenCenter(1,"Tuincentrum Geel","Geel","Geelstraat 2"));
            gardenCenterRepository.save(new GardenCenter(2, "Plantenhuis","Aarschot" ,"Plantstraat 16"));
            gardenCenterRepository.save(new GardenCenter( 3, "TuinMagazijn","Brussel", "Tuinstraat 34"));
            gardenCenterRepository.save(new GardenCenter( 4, "GreenGarden", "Geel","Groenstraat 34"));
        }
    }

    @GetMapping("/gardencenters")
    public List<GardenCenter> getPlants() {
        return gardenCenterRepository.findAll();
    }

    @GetMapping("/gardencenters/location/address/{address}")
    public List<GardenCenter> getGardenCentersByAddress(@PathVariable String address){
        return gardenCenterRepository.findAllByAddressLike(address);
    }

    @GetMapping("/gardencenters/city/{city}")
    public List<GardenCenter> getGardenCentersByCity(@PathVariable String city){
        return gardenCenterRepository.findAllByAddressLike(city);
    }

    @GetMapping("/gardencenters/name/{name}")
    public List<GardenCenter> getGardenCentersByName(@PathVariable String name){
        return gardenCenterRepository.findAllByNameLike(name);
    }
    @GetMapping("/gardencenters/{id}")
    public GardenCenter getGardenCenterById(@PathVariable int id){
        return gardenCenterRepository.getGardenCenterById(id);
    }
}
