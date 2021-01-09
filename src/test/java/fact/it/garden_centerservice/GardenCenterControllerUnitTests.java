package fact.it.garden_centerservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.garden_centerservice.model.GardenCenter;
import fact.it.garden_centerservice.repository.GardenCenterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GardenCenterControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GardenCenterRepository gardenCenterRepository;

    @Test
    public void givenGardenCenter_ReturnJsonGardenCenters() throws Exception {
        GardenCenter gardenCenter1 = new GardenCenter(1,"Tuincentrum","Geel","Geelstraat 2");
        GardenCenter gardenCenter2 = new GardenCenter(2, "Plantenhuis","Aarschot" ,"Plantstraat 16");
        GardenCenter gardenCenter3 = new GardenCenter( 3, "GreenGarden", "Geel","Groenstraat 34");

        List<GardenCenter> gardenCenterList = new ArrayList<>();
        gardenCenterList.add(gardenCenter1);
        gardenCenterList.add(gardenCenter2);
        gardenCenterList.add(gardenCenter3);

        given(gardenCenterRepository.findAll()).willReturn(gardenCenterList);

        mockMvc.perform(get("/gardencenters"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].gardenCenterId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Tuincentrum")))
                .andExpect(jsonPath("$[0].city", is("Geel")))
                .andExpect(jsonPath("$[0].address", is("Geelstraat 2")))
                .andExpect(jsonPath("$[1].gardenCenterId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Plantenhuis")))
                .andExpect(jsonPath("$[1].city", is("Aarschot")))
                .andExpect(jsonPath("$[1].address", is("Plantstraat 16")))
                .andExpect(jsonPath("$[2].gardenCenterId", is(3)))
                .andExpect(jsonPath("$[2].name", is("GreenGarden")))
                .andExpect(jsonPath("$[2].city", is("Geel")))
                .andExpect(jsonPath("$[2].address", is("Groenstraat 34")));
    }

    @Test
    public void givenGardenCenter_whenGetGardenCentersByCity_thenReturnJsonGardenCenters() throws Exception {

        GardenCenter gardenCenter1 = new GardenCenter(1,"Tuincentrum","Geel","Geelstraat 2");
        GardenCenter gardenCenter3 = new GardenCenter( 3, "GreenGarden", "Geel","Groenstraat 34");

        List<GardenCenter> gardenCenterList = new ArrayList<>();
        gardenCenterList.add(gardenCenter1);
        gardenCenterList.add(gardenCenter3);

        given(gardenCenterRepository.findAllByCityLike("Geel")).willReturn(gardenCenterList);

        mockMvc.perform(get("/gardencenters/city/{city}", "Geel"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].gardenCenterId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Tuincentrum")))
                .andExpect(jsonPath("$[0].city", is("Geel")))
                .andExpect(jsonPath("$[0].address", is("Geelstraat 2")))
                .andExpect(jsonPath("$[1].gardenCenterId", is(3)))
                .andExpect(jsonPath("$[1].name", is("GreenGarden")))
                .andExpect(jsonPath("$[1].city", is("Geel")))
                .andExpect(jsonPath("$[1].address", is("Groenstraat 34")));
    }

    @Test
    public void givenGardenCenter_whenGetGardenCentersByAddress_thenReturnJsonGardenCenters() throws Exception {

        GardenCenter gardenCenter2 = new GardenCenter(2, "Plantenhuis","Aarschot" ,"Plantstraat 16");

        List<GardenCenter> gardenCenterList = new ArrayList<>();
        gardenCenterList.add(gardenCenter2);

        given(gardenCenterRepository.findAllByAddressLike("Plantstraat")).willReturn(gardenCenterList);

        mockMvc.perform(get("/gardencenters/address/{address}", "Plantstraat"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].gardenCenterId", is(2)))
                .andExpect(jsonPath("$[0].name", is("Plantenhuis")))
                .andExpect(jsonPath("$[0].city", is("Aarschot")))
                .andExpect(jsonPath("$[0].address", is("Plantstraat 16")));
    }

    @Test
    public void givenGardenCenter_whenGetGardenCentersByName_thenReturnJsonGardenCenters() throws Exception {
        GardenCenter gardenCenter3 = new GardenCenter( 3, "GreenGarden", "Geel","Groenstraat 34");

        List<GardenCenter> gardenCenterList = new ArrayList<>();
        gardenCenterList.add(gardenCenter3);

        given(gardenCenterRepository.findAllByNameLike("GreenGarden")).willReturn(gardenCenterList);

        mockMvc.perform(get("/gardencenters/name/{name}", "GreenGarden"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].gardenCenterId", is(3)))
                .andExpect(jsonPath("$[0].name", is("GreenGarden")))
                .andExpect(jsonPath("$[0].city", is("Geel")))
                .andExpect(jsonPath("$[0].address", is("Groenstraat 34")));
    }

    @Test
    public void givenGardenCenter_whenGetGardenCentersByGardenCenterId_thenReturnJsonGardenCenter() throws Exception {
        GardenCenter gardenCenter1 = new GardenCenter(1,"Tuincentrum","Geel","Geelstraat 2");

        given(gardenCenterRepository.getGardenCenterByGardenCenterId(1)).willReturn(gardenCenter1);

        mockMvc.perform(get("/gardencenters/{id}",1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gardenCenterId", is(1)))
                .andExpect(jsonPath("$.name", is("Tuincentrum")))
                .andExpect(jsonPath("$.city", is("Geel")))
                .andExpect(jsonPath("$.address", is("Geelstraat 2")));
    }
}
