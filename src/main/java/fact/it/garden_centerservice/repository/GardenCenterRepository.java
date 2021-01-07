package fact.it.garden_centerservice.repository;


import fact.it.garden_centerservice.model.GardenCenter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GardenCenterRepository extends MongoRepository<GardenCenter, String> {
    GardenCenter findGardenCenterByName(String name);
    List<GardenCenter> getAllBy();
    List<GardenCenter> findAllByAddressLike(String address);
    List<GardenCenter> findAllByCityLike(String city);
    List<GardenCenter> findAllByNameLike(String name);
}
