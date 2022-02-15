package repositories;

import com.google.inject.ImplementedBy;
import models.Airport;
import models.Country;
import models.Runway;

import javax.inject.Singleton;
import java.util.List;

@ImplementedBy(LocalCSVRepo.class)
public interface CSVRepository{

    void addAirport(Airport airport);
    List<Airport> getAirports();

    void addRunway(Runway runway);
    List<Runway> getRunways();

    void addCountry(Country country);
    List<Country> getCountries();

}
