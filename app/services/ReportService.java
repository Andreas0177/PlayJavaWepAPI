package services;

import models.Airport;
import models.Report;
import repositories.CSVRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    public static Report getReport(CSVRepository repo){

        Report report =new Report();

        HashMap<String,Integer> countryCounter= new HashMap<String,Integer>();

        for(Airport airport : repo.getAirports()) {
            Integer counter = countryCounter.get(airport.getCountry());
            if(counter==null)
                countryCounter.put(airport.getCountry(), 1);
            else
                countryCounter.put(airport.getCountry(), ++counter);
        }

        HashMap<String, Integer> top10=countryCounter.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
        HashMap<String, Integer> bottom10=countryCounter.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()).limit(10).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));

        report.setTop10(top10);
        report.setBottom10(bottom10);

        return report;

    }

}
