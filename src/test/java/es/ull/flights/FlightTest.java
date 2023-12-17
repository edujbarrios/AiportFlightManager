package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Flight.
 */
public class FlightTest {

    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        flight = new Flight("AB123", 2);
        passenger = new Passenger("ID123", "John Doe", "US");
    }

    @Test
    void testFlightConstructorValid() {
        assertEquals("AB123", flight.getFlightNumber());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testFlightConstructorInvalid() {
        assertThrows(RuntimeException.class, () -> new Flight("123AB", 10));
    }

    @Test
    void testAddPassenger() {
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testAddPassengerFullFlight() {
        flight.addPassenger(passenger);
        Passenger anotherPassenger = new Passenger("ID456", "Jane Doe", "GB");
        assertTrue(flight.addPassenger(anotherPassenger));

        Passenger thirdPassenger = new Passenger("ID789", "Alice Doe", "FR");
        assertThrows(RuntimeException.class, () -> flight.addPassenger(thirdPassenger));
    }

    @Test
    void testRemovePassenger() {
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testRemoveNonExistingPassenger() {
        assertFalse(flight.removePassenger(passenger));
    }
}

