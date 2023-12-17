package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * Representa un vuelo con un número de vuelo específico y capacidad limitada de asientos.
 */
public class Flight {

    private String flightNumber; // Número de vuelo
    private int seats; // Número de asientos
    private Set<Passenger> passengers = new HashSet<>(); // Conjunto de pasajeros

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$"; // Regex para validar el número de vuelo
    private static Pattern pattern = Pattern.compile(flightNumberRegex); // Patrón para validación de regex

    /**
     * Constructor para crear un nuevo vuelo.
     *
     * @param flightNumber El número de vuelo, debe seguir el patrón especificado.
     * @param seats El número total de asientos disponibles en el vuelo.
     * @throws RuntimeException Si el número de vuelo no es válido.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * Obtiene el número de vuelo.
     *
     * @return El número de vuelo.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Obtiene el número de pasajeros actualmente en el vuelo.
     *
     * @return El número de pasajeros.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * Añade un pasajero al vuelo.
     *
     * @param passenger El pasajero a añadir.
     * @return true si el pasajero fue añadido con éxito, false de lo contrario.
     * @throws RuntimeException Si no hay suficientes asientos disponibles.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * Elimina un pasajero del vuelo.
     *
     * @param passenger El pasajero a eliminar.
     * @return true si el pasajero fue eliminado con éxito, false de lo contrario.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
