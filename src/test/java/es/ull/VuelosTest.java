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

    @Test
    @DisplayName("Crear vuelo con número de vuelo en minúsculas lanza excepción")
    void crearVueloConNumeroEnMinusculas() {
        assertThrows(RuntimeException.class, () -> new Flight("ib001", 150));
    }
    @Test
    @DisplayName("Intentar eliminar un pasajero que no está en el vuelo no cambia la cantidad de pasajeros")
    void intentarEliminarPasajeroNoExistente() {
        int numeroPasajerosInicial = vuelo.getNumberOfPassengers();
        vuelo.removePassenger(new Passenger("999", "Pedro", "US"));
        assertEquals(numeroPasajerosInicial, vuelo.getNumberOfPassengers());
    }
    @Test
    @DisplayName("Crear vuelo con número de vuelo nulo lanza excepción")
    void crearVueloConNumeroNulo() {
        assertThrows(RuntimeException.class, () -> new Flight(null, 150));
    }
    @Test
    @DisplayName("Crear vuelo con número negativo de asientos lanza excepción")
    void crearVueloConAsientosNegativos() {
        assertThrows(RuntimeException.class, () -> new Flight("IB0-4", -1));
    }
    @Test
    @DisplayName("Agregar un pasajero nulo lanza excepción")
    void agregarPasajeroNulo() {
        assertThrows(RuntimeException.class, () -> vuelo.addPassenger(null));
    }
    @Test
    @DisplayName("Vuelo alcanza capacidad máxima correctamente")
    void vueloAlcanzaCapacidadMaxima() {
        vuelo.addPassenger(pasajero1);
        vuelo.addPassenger(pasajero2);
        assertEquals(2, vuelo.getNumberOfPassengers());
    }
    @Test
    @DisplayName("Crear vuelo con número de vuelo de longitud mínima y máxima")
    void crearVueloConLongitudNumeroVuelo() {
        Flight vueloMin = new Flight("AB123", 10);
        Flight vueloMax = new Flight("AB1234", 10);
        assertEquals("AB123", vueloMin.getFlightNumber());
        assertEquals("AB1234", vueloMax.getFlightNumber());
    }

    @Test
    @DisplayName("Agregar pasajeros con el mismo nombre pero diferente ID")
    void agregarPasajerosNombreIgualIdDiferente() {
        Passenger pasajeroDuplicado = new Passenger("789", "Lucía", "ES");
        vuelo.addPassenger(pasajero1);
        assertTrue(vuelo.addPassenger(pasajeroDuplicado));
        assertEquals(2, vuelo.getNumberOfPassengers());
    }
    @Test
    @DisplayName("Crear vuelo con cero asientos")
    void crearVueloConCeroAsientos() {
        Flight vueloCeroAsientos = new Flight("IB005", 0);
        assertThrows(RuntimeException.class, () -> vueloCeroAsientos.addPassenger(pasajero1));
    }

    @Test
    @DisplayName("Intentar agregar pasajeros más allá de la capacidad máxima lanza excepción")
    void agregarPasajerosMasAllaCapacidad() {
        Flight vueloUnAsiento = new Flight("IB006", 1);
        vueloUnAsiento.addPassenger(pasajero1);
        assertThrows(RuntimeException.class, () -> vueloUnAsiento.addPassenger(pasajero2));
    }
    @Test
    @DisplayName("Verificar lista de pasajeros después de múltiples adiciones y eliminaciones")
    void verificarListaPasajerosDespuesEliminaciones() {
        vuelo.addPassenger(pasajero1);
        vuelo.addPassenger(pasajero2);
        vuelo.removePassenger(pasajero1);
        vuelo.addPassenger(pasajero1);
        assertEquals(2, vuelo.getNumberOfPassengers());
    }
    @Test
    @DisplayName("Crear vuelo con número de vuelo con caracteres especiales lanza excepción")
    void crearVueloConCaracteresEspeciales() {
        assertThrows(RuntimeException.class, () -> new Flight("IB@007", 10));
    }
}
