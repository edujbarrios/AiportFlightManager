package es.ull.passengers;

import es.ull.flights.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsPasajeros {

    private Passenger pasajero1;
    private Passenger pasajero2;
    private Passenger pasajero3;
    private Flight vuelo1;
    private Flight vuelo2;

    @BeforeEach
    void setUp() {
        pasajero1 = new Passenger("123", "Lucía", "ES");
        pasajero2 = new Passenger("456", "Juan", "GB");
        pasajero3 = new Passenger("789", "Sara", "FR");
        vuelo1 = new Flight("IB001", 3);
        vuelo2 = new Flight("BA002", 2);
    }

    @Test
    @DisplayName("Obtener nombre de pasajero")
    void obtenerNombre() {
        assertEquals("Lucía", pasajero1.getName());
    }

    @Test
    @DisplayName("Obtener identificador de pasajero")
    void obtenerIdentificador() {
        assertEquals("456", pasajero2.getIdentifier());
    }

    @Test
    @DisplayName("Comprobar código de país del pasajero")
    void obtenerCodigoPais() {
        assertEquals("FR", pasajero3.getCountryCode());
    }

    @Test
    @DisplayName("Comprobar vuelo asignado al pasajero")
    void comprobarVueloAsignado() {
        pasajero2.setFlight(vuelo1);
        assertEquals(vuelo1, pasajero2.getFlight());
    }

    @Test
    @DisplayName("Control de excepción para código de país inválido")
    void controlExcepcionCodigoPais() {
        assertThrows(RuntimeException.class, () -> new Passenger("1010", "Elena", "ZZ"));
    }

    @Test
    @DisplayName("Unirse a un vuelo disponible")
    void unirseAVueloDisponible() {
        pasajero3.joinFlight(vuelo2);
        assertEquals(vuelo2, pasajero3.getFlight());
    }

    @Test
    @DisplayName("Comprobar la impresión del pasajero")
    void imprimirInformacionPasajero() {
        String esperado = "Passenger Juan with identifier: 456 from GB";
        assertEquals(esperado, pasajero2.toString());
    }

    @Test
    @DisplayName("Intento de unirse a vuelo lleno")
    void intentoUnirseAVueloLleno() {
        pasajero1.joinFlight(vuelo2);
        pasajero2.joinFlight(vuelo2);
        assertThrows(RuntimeException.class, () -> pasajero3.joinFlight(vuelo2));
    }

    @Test
    @DisplayName("Remoción de pasajero de un vuelo")
    void removerPasajeroDeVuelo() {
        pasajero1.joinFlight(vuelo1);
        pasajero1.setFlight(null);
        assertNull(pasajero1.getFlight());
    }

    @Test
    @DisplayName("Comprobación de vuelo nulo en nuevo pasajero")
    void comprobarVueloNuloEnNuevoPasajero() {
        assertNull(pasajero3.getFlight());
    }

    @Test
    @DisplayName("Asignación de vuelo a un pasajero ya en otro vuelo")
    void cambiarVueloPasajero() {
        pasajero2.joinFlight(vuelo1);
        pasajero2.setFlight(vuelo2);
        assertEquals(vuelo2, pasajero2.getFlight());
    }

    @Test
    @DisplayName("Comparar igualdad de dos pasajeros")
    void compararPasajeros() {
        Passenger pasajeroDuplicado = new Passenger("123", "Lucía", "ES");
        assertEquals(pasajero1.getIdentifier(), pasajeroDuplicado.getIdentifier());
        assertEquals(pasajero1.getName(), pasajeroDuplicado.getName());
        assertEquals(pasajero1.getCountryCode(), pasajeroDuplicado.getCountryCode());
    }

    @Test
    @DisplayName("Verificar consistencia en toString")
    void verificarToString() {
        String esperado = "Passenger Sara with identifier: 789 from FR";
        assertEquals(esperado, pasajero3.toString());
    }

    @Test
    @DisplayName("Verificar consistencia en getIdentifier")
    void verificarGetIdentifier() {
        assertEquals("789", pasajero3.getIdentifier());
    }

    @Test
    @DisplayName("Asignar vuelo nulo a un pasajero")
    void asignarVueloNulo() {
        pasajero1.joinFlight(vuelo1);
        pasajero1.setFlight(null);
        assertNull(pasajero1.getFlight());
    }

    @Test
    @DisplayName("Excepción al agregar pasajero a vuelo lleno")
    void excepcionAlAgregarPasajeroAVueloLleno() {
        pasajero1.joinFlight(vuelo1);
        pasajero2.joinFlight(vuelo1);
        pasajero3.joinFlight(vuelo1);
        Passenger pasajeroExtra = new Passenger("1011", "Marta", "US");
        assertThrows(RuntimeException.class, () -> pasajeroExtra.joinFlight(vuelo1));
    }

}

