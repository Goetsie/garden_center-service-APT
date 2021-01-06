package fact.it.garden_centerservice.model;


import org.springframework.data.annotation.Id;


public class GardenCenter {

    @Id
    private String id;
    private String name;
    private String adres;

    public GardenCenter() {
    }

    public GardenCenter(String id, String name, String adres) {
        this.id = id;
        this.name = name;
        this.adres = adres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
