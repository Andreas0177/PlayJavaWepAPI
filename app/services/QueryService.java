package services;

import models.Airport;
import models.Country;
import models.Runway;
import repositories.CSVRepository;

import java.util.*;

public class QueryService {

    public static List<Airport> getQueryResult(CSVRepository repo,String input){

        String selectedCountryCode=null;

        if(input==null) return null;

        for (Country record : repo.getCountries()) {
            if(record.getCode().equals(input) || record.getName().equals(input)){
                selectedCountryCode=record.getCode();
                break;
            }
        }

        List<Airport> AirportsOfSelectedCountry=new ArrayList<>();
        System.out.println("selectedCountryCode:"+selectedCountryCode);

        if(selectedCountryCode!=null){

            for (Airport record : repo.getAirports()) {//read airports csv line
                if(record.getCountry().equals(selectedCountryCode))
                    AirportsOfSelectedCountry.add(record);
            }

            for (Runway runway : repo.getRunways()) {//read runways csv line

                Airport tempAirport=getAirport(AirportsOfSelectedCountry,runway.getAirportId());

                if(tempAirport!=null)
                    tempAirport.getRunwayList().add(runway);
            }
            System.out.println("FINISHED");

        }else
            System.out.println( "Error: Country not found");

        return AirportsOfSelectedCountry;
    }

    private static Airport getAirport(List<Airport> airports, String id){
        for(Airport airport : airports)
            if(airport.getId().equals(id)) return airport;
        return null;
    }
}
