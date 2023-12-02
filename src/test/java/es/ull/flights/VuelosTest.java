package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Crear vuelo con número válido")
    void crearVueloValido() {
        Flight nuevoVuelo = new Flight("BA123", 150);
        assertEquals("BA123", nuevoVuelo.getFlightNumber());
    }

    @Test
    @DisplayName("Crear vuelo con número inválido lanza excepción")
    void crearVueloInvalido() {
        assertThrows(RuntimeException.class, () -> new Flight("Invalido123", 150));
    }

    @Test
    @DisplayName("Agregar pasajero a un vuelo")
    void agregarPasajero() {
        assertTrue(vuelo.addPassenger(pasajero1));
        assertEquals(1, vuelo.getNumberOfPassengers());
    }

    @Test
    @DisplayName("Agregar pasajero a un vuelo lleno lanza excepción")
    void agregarPasajeroVueloLleno() {
        vuelo.addPassenger(pasajero1);
        vuelo.addPassenger(pasajero2);
        Passenger pasajero3 = new Passenger("789", "Sara", "FR");
        assertThrows(RuntimeException.class, () -> vuelo.addPassenger(pasajero3));
    }

    @Test
    @DisplayName("Eliminar pasajero de un vuelo")
    void eliminarPasajero() {
        vuelo.addPassenger(pasajero1);
        assertTrue(vuelo.removePassenger(pasajero1));
        assertEquals(0, vuelo.getNumberOfPassengers());
    }

    @Test
    @DisplayName("Intentar eliminar pasajero no presente no cambia cantidad de pasajeros")
    void eliminarPasajeroNoPresente() {
        vuelo.addPassenger(pasajero1);
        vuelo.removePassenger(pasajero2); // pasajero2 no está en el vuelo
        assertEquals(1, vuelo.getNumberOfPassengers());
    }

    @Test
    @DisplayName("Obtener número de pasajeros en un vuelo")
    void obtenerNumeroDePasajeros() {
        vuelo.addPassenger(pasajero1);
        vuelo.addPassenger(pasajero2);
        assertEquals(2, vuelo.getNumberOfPassengers());
    }

    @Test
    @DisplayName("Agregar el mismo pasajero dos veces no debería incrementar el número de pasajeros")
    void agregarPasajeroDuplicado() {
        vuelo.addPassenger(pasajero1);
        vuelo.addPassenger(pasajero1); // Intentando agregar el mismo pasajero otra vez
        assertEquals(1, vuelo.getNumberOfPassengers());
    }


    @Test
    @DisplayName("Eliminar y luego agregar el mismo pasajero debería ser posible")
    void eliminarYAgregarMismoPasajero() {
        vuelo.addPassenger(pasajero1);
        vuelo.removePassenger(pasajero1);
        vuelo.addPassenger(pasajero1); // Reagregando el mismo pasajero
        assertEquals(1, vuelo.getNumberOfPassengers());
    }

    @Test
    @DisplayName("Intentar eliminar un pasajero que no está en el vuelo no debería afectar el número de pasajeros")
    void eliminarPasajeroNoExistente() {
        vuelo.addPassenger(pasajero1);
        vuelo.removePassenger(pasajero2); // Pasajero2 no está en el vuelo
        assertEquals(1, vuelo.getNumberOfPassengers());
    }

    @Test
    @DisplayName("Verificar que un pasajero se agregue sólo si hay asientos disponibles")
    void verificarAsientosDisponiblesAlAgregarPasajero() {
        Flight vueloConUnAsiento = new Flight("IB002", 1);
        vueloConUnAsiento.addPassenger(pasajero1);
        assertThrows(RuntimeException.class, () -> vueloConUnAsiento.addPassenger(pasajero2));
    }
}
