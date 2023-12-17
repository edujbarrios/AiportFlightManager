package es.ull.fligts;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VuelosTest {

    private Flight vuelo;
    private Passenger pasajero1;
    private Passenger pasajero2;

    @BeforeEach
    void setUp() {
        vuelo = new Flight("IB001", 2);
        pasajero1 = new Passenger("123", "Lucía", "ES");
        pasajero2 = new Passenger("456", "Juan", "GB");
    }

    @Test
    void testFlightConstructorValid() {
        assertDoesNotThrow(() -> new Flight("IB002", 3));
    }

    @Test
    void testFlightConstructorInvalid() {
        assertThrows(RuntimeException.class, () -> new Flight("123", 3));
    }

    @Test
    void testGetFlightNumber() {
        assertEquals("IB001", vuelo.getFlightNumber());
    }

    @Test
    void testInitialPassengerCount() {
        assertEquals(0, vuelo.getNumberOfPassengers());
    }

    @Test
    void testAddPassenger() {
        assertTrue(vuelo.addPassenger(pasajero1));
        assertEquals(1, vuelo.getNumberOfPassengers());
    }

    @Test
    void testAddPassengerWhenFull() {
        vuelo.addPassenger(pasajero1);
        vuelo.addPassenger(pasajero2);
        Passenger pasajero3 = new Passenger("789", "Pedro", "FR");
        assertThrows(RuntimeException.class, () -> vuelo.addPassenger(pasajero3));
    }

    @Test
    void testAddDuplicatePassenger() {
        vuelo.addPassenger(pasajero1);
        assertFalse(vuelo.addPassenger(pasajero1));
    }

    @Test
    void testRemovePassenger() {
        vuelo.addPassenger(pasajero1);
        assertTrue(vuelo.removePassenger(pasajero1));
        assertEquals(0, vuelo.getNumberOfPassengers());
    }

    @Test
    void testRemoveNonExistingPassenger() {
        assertFalse(vuelo.removePassenger(pasajero1));
    }
}
