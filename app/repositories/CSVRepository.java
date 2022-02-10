package repositories;

import models.Airport;
import models.Country;
import models.Runway;

import java.util.List;

public interface CSVRepository {

    void addAirport(Airport airport);
    List<Airport> getAirports();

    void addRunway(Runway runway);
    List<Runway> getRunways();

    void addCountry(Country country);
    List<Country> getCountries();

}
