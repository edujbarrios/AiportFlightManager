package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;

import es.ull.flights.Flight;

/**
 * Representa un pasajero con identificador, nombre y código de país.
 */
public class Passenger {

    private String identifier; // Identificador único del pasajero
    private String name; // Nombre del pasajero
    private String countryCode; // Código de país del pasajero
    private Flight flight; // Vuelo en el que está el pasajero

    /**
     * Constructor para crear un nuevo pasajero.
     *
     * @param identifier El identificador único del pasajero.
     * @param name El nombre del pasajero.
     * @param countryCode El código de país ISO del pasajero.
     * @throws RuntimeException Si el código de país no es válido.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * Obtiene el identificador del pasajero.
     *
     * @return El identificador del pasajero.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Obtiene el nombre del pasajero.
     *
     * @return El nombre del pasajero.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el código de país del pasajero.
     *
     * @return El código de país del pasajero.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Obtiene el vuelo asignado al pasajero.
     *
     * @return El vuelo del pasajero.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Asigna al pasajero a un vuelo, quitándolo del anterior si lo hubiera.
     *
     * @param flight El nuevo vuelo a asignar.
     * @throws RuntimeException Si no se puede agregar o eliminar al pasajero del vuelo.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (previousFlight != null) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (flight != null) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * Establece o actualiza el vuelo del pasajero.
     *
     * @param flight El vuelo a asignar al pasajero.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Devuelve una representación en cadena del pasajero.
     *
     * @return Una cadena que representa al pasajero.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
