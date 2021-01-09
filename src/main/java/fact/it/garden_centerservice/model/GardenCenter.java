package fact.it.garden_centerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GardenCenters")
public class GardenCenter {

    @Id
    private String id;
    @Indexed(unique = true)
    private int gardenCenterId;
    private String name;
    private String city;
    private String address;

//    public GardenCenter() {
//    }

    public GardenCenter(int gardenCenterId, String name, String city, String address) {
        setGardenCenterId(gardenCenterId);
        setName(name);
        setCity(city);
        setAddress(address);
    }


    public int getGardenCenterId() {
        return gardenCenterId;
    }

    public void setGardenCenterId(int gardenCenterId) {
        this.gardenCenterId = gardenCenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
