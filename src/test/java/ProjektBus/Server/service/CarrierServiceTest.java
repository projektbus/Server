package ProjektBus.Server.service;

import ProjektBus.Server.model.Carrier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrierServiceTest {

    @Autowired
    private CarrierService carrierService;

    @Test
    public void shouldSaveCarrier() {
        Carrier carrier = carrierService.addCarrier(new Carrier("name"));
        assertNotNull(carrier);
    }

}