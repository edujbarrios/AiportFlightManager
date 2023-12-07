package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import es.ull.flights.Flight;

public class Passenger {
    private String identifier;
    private String name;
    private String countryCode;
    private Flight flight;

    public Passenger(String identifier, String name, String countryCode) {
        validateCountryCode(countryCode);
        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    private void validateCountryCode(String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new IllegalArgumentException("Invalid country code");
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void joinFlight(Flight flight) {
        if (this.flight != null) {
            if (!this.flight.removePassenger(this)) {
                throw new IllegalStateException("Cannot remove passenger from previous flight");
            }
        }
        setFlight(flight);
        if (flight != null && !flight.addPassenger(this)) {
            throw new IllegalStateException("Cannot add passenger to new flight");
        }
    }

    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}

