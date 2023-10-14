package com.udacity.pricing.tests;

import com.udacity.pricing.events.PriceEventHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceEventHandler.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PricingServiceUnitTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    PriceEventHandler priceEventHandler;

    @Test
    public void getPriceFromVehicleId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/prices/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk());
        Mockito.verify(this.priceEventHandler, times(1)).getPrice(1L);
    }
}
