package repositories;

import models.Airport;
import models.Country;
import models.Runway;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class LocalCSVRepo implements CSVRepository{

    List<Airport> airports=new ArrayList<>();//?
    List<Runway> runways=new ArrayList<>();
    List<Country> countries=new ArrayList<>();

    //final static String countriesCSV="C:\\Projects\\webAPI4\\app\\resources\\countries.csv";//wrong must be changed with relative path
    //final static String airportsCSV="C:\\Projects\\webAPI4\\app\\resources\\airports.csv";
    //final static String runwaysCSV="C:\\Projects\\webAPI4\\app\\resources\\runways.csv";

    final static String countriesCSV="C:\\Projects\\webAPI4\\app\\resources\\countries.csv";//wrong must be changed with relative path
    final static String airportsCSV="C:\\Projects\\webAPI4\\app\\resources\\airports.csv";
    final static String runwaysCSV="C:\\Projects\\webAPI4\\app\\resources\\runways.csv";

    @Inject
    public LocalCSVRepo(){
        //load local csv files

        try{


            Reader in = new FileReader(airportsCSV);
            Iterable<CSVRecord> records= CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) //read airports csv line
                addAirport(new Airport(record.get("id"),record.get("name"),record.get("iso_country")));

            in = new FileReader(runwaysCSV);
            records= CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) //read runways csv line
                addRunway(new Runway(record.get("le_ident"),record.get("surface"),record.get("airport_ref")));

            in = new FileReader(countriesCSV);
            records= CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) //read countries csv line
                addCountry(new Country(record.get("name"),record.get("code")));


        }catch(IOException e){
            System.out.println("Server IO Error");
        }
    }

    public Airport getAirport(String id){

        return null;
    }

    public Airport getRunways(String id){

        return null;
    }

    @Override
    public void addAirport(Airport airport) {
        airports.add(airport);
    }

    @Override
    public List<Airport> getAirports() {
        return airports;
    }

    @Override
    public void addRunway(Runway runway) {
        runways.add(runway);
    }

    @Override
    public List<Runway> getRunways() {
        return runways;
    }

    @Override
    public void addCountry(Country country) {
        countries.add(country);
    }

    @Override
    public List<Country> getCountries() {
        return countries;
    }
}
