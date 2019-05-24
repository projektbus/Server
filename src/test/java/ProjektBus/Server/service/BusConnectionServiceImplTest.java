package ProjektBus.Server.service;

import ProjektBus.Server.model.BusConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusConnectionServiceImplTest {

    @Autowired
    private BusConnectionService busConnectionService;

    @Test
    public void shouldSaveConnection() {
        LocalTime time = LocalTime.now();
        BusConnection busConnection = busConnectionService.addBusConnection(new BusConnection("1", "2", "3", time));

        assertNotNull(busConnection);
        assertNotNull(busConnection.getDepartureTime());
    }

    @Test
    public  void shouldDeleteConnection() {
        LocalTime time = LocalTime.now();
        BusConnection busConnection = busConnectionService.addBusConnection(new BusConnection("1","2","3",time));
        busConnectionService.deleteBusConnection(busConnection);
        assertFalse(busConnectionService.getAllBusConnections().contains(busConnection));

    }

    @Test
    public  void shouldFindConnectionByLineAndByStop() {
        LocalTime time = LocalTime.now();

        BusConnection busConnection1 = busConnectionService.addBusConnection(new BusConnection("1","2","3",time));
        BusConnection busConnection2 = busConnectionService.addBusConnection(new BusConnection("1","2","3",time));
        BusConnection busConnection3 = busConnectionService.addBusConnection(new BusConnection("2","5","4",time));

        assertNotEquals(busConnectionService.getBusConnectionByLineAndStop("1", "4").size(), 2);
        assertEquals(busConnectionService.getBusConnectionByLineAndStop("1", "3").size(), 2);
        assertEquals(busConnectionService.getBusConnectionByLineAndStop("2", "4").stream().filter(e->e.getCarrierId().equals("5")).count(),1);

    }


}
