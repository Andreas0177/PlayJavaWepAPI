package handlers;

import models.Report;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.inject.Singleton;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Singleton
public class CSVReader {

    final static String countriesCSV="C:\\Projects\\webAPI4\\app\\resources\\countries.csv";
    final static String airportsCSV="C:\\Projects\\webAPI4\\app\\resources\\airports.csv";
    final static String runwaysCSV="C:\\Projects\\webAPI4\\app\\resources\\runways.csv";

    FileReader incountriesCSV;
    FileReader inairportsCSV;
    FileReader inrunwaysCSV;

    public CSVReader() throws FileNotFoundException {
        incountriesCSV = new FileReader(countriesCSV);
        inairportsCSV = new FileReader(airportsCSV);
        inrunwaysCSV = new FileReader(runwaysCSV);
    }


    public static HashMap<String, List<String>> getAirPortsOfSelectedCountry(String input){

        String selectedCountryCode=null;
        HashMap<String, List<String>> InfOfAirportsOfSelectedCountry=null;

        try{

            //FileReader in = new FileReader(countriesCSV);
            //Iterable<CSVRecord> records=CSVFormat.DEFAULT.withHeader().parse(in);
            Iterable<CSVRecord> records=CSVFormat.DEFAULT.withHeader().parse(incountriesCSV);

            for (CSVRecord record : records) {
                if(record.get("code").equals(input) || record.get("name").equals(input)){
                    selectedCountryCode=record.get("code");
                    break;
                }
            }

            //fuzzy select, we cant place inside the 1st loop because a fuzzy select G
            if(selectedCountryCode==null){
                for (CSVRecord record : records) {
                    if(record.get("name").contains(input)){
                        selectedCountryCode=record.get("code");
                        break;
                    }
                }
            }

            System.out.println("selectedCountryCode:"+selectedCountryCode);
            if(selectedCountryCode!=null){

                //in = new FileReader(airportsCSV);
                records=CSVFormat.DEFAULT.withHeader().parse(inairportsCSV);

                Map<Integer, String> AirportsOfSelectedCountry = new HashMap<Integer, String>();

                for (CSVRecord record : records) {//read airports csv line

                    String country=record.get("iso_country");
                    int id=Integer.parseInt(record.get("id"));
                    if(country.equals(selectedCountryCode))
                        AirportsOfSelectedCountry.put( Integer.valueOf(record.get("id")),record.get("name"));

                }

                InfOfAirportsOfSelectedCountry = new HashMap<String, List<String>>();

                //in = new FileReader(runwaysCSV);
                records=CSVFormat.DEFAULT.withHeader().parse(inrunwaysCSV);

                for (CSVRecord record : records) {//read runways csv line

                    int airportId=Integer.parseInt(record.get("airport_ref"));

                    if(AirportsOfSelectedCountry.get(airportId)!=null){

                        if(InfOfAirportsOfSelectedCountry.get(airportId)!=null){
                            System.out.println("++");
                            InfOfAirportsOfSelectedCountry.get(airportId).add(record.get("id")) ;//add the id of runway

                        }else{//new entry

                            InfOfAirportsOfSelectedCountry.put(String.valueOf(AirportsOfSelectedCountry.get(airportId)),//name
                                    Arrays.asList(record.get("id")));//id of runway
                        }
                    }
                }


                //InfOfAirportsOfSelectedCountry.forEach((k, v) -> System.out.println((k + ":" + v)));

                System.out.println("FINISHED");

            }else
                System.out.println( "Error: Country not found");
        }catch(IOException e){
            System.out.println("Server Error:"+e);
        }
        return InfOfAirportsOfSelectedCountry;
    }

    public static Report getReport(){

        Report report =new Report();

        try{
            Map<String, Integer> NumberOfAirportsByCountry = new HashMap<String, Integer>();
            Map<Integer, String> AirportsByCountry = new HashMap<Integer, String>();

            Reader in = new FileReader(airportsCSV);
            Iterable<CSVRecord> records=CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) {//read airports csv line

                String country=record.get("iso_country");
                int id=Integer.parseInt(record.get("id"));

                if (NumberOfAirportsByCountry.get(country)==null){
                    NumberOfAirportsByCountry.put(country, 1);
                }else{//increase by one
                    int counter=NumberOfAirportsByCountry.get(country)+1;
                    NumberOfAirportsByCountry.replace(country, counter);
                }

                AirportsByCountry.put(id,country);
            }
            //sort ascending by value and take the 10 first HashMap<String, Integer>
            //String sortedAsc = NumberOfAirportsByCountry.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()).limit(10);
            //sort descending by value and take the 10 first
            //Stream<Map.Entry<String, Integer>> sortedDesc =
            HashMap<String, Integer> top10=NumberOfAirportsByCountry.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
            HashMap<String, Integer> bottom10=NumberOfAirportsByCountry.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()).limit(10).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));

            report.setTop10(top10);
            report.setBottom10(bottom10);

            //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

            HashMap<String, Integer> RunwayIDS = new HashMap<String, Integer>();
            HashMap<String, List<String> > typeOfRunwaysByCountries = new HashMap<String, List<String> >();

            in = new FileReader(runwaysCSV);
            records=CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) {//read runway csv line

                String RWID = record.get("le_ident");
                String type = record.get("surface");
                int id=Integer.parseInt(record.get("airport_ref"));

                if (RunwayIDS.get(RWID)==null){
                    RunwayIDS.put(RWID, 1);
                }else{
                    int counter = RunwayIDS.get(RWID);
                    counter++;
                    RunwayIDS.replace(RWID, counter);
                }

                //--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*

                String country=AirportsByCountry.get(id);
                //if the country does not exist yet
                if (typeOfRunwaysByCountries.get(country)==null){
                    List<String> types= new ArrayList<String>();
                    types.add(type);
                    typeOfRunwaysByCountries.put(country, types);//create new entry
                }else if(!typeOfRunwaysByCountries.get(country).contains(type)){//if the type does not exist for this country
                    typeOfRunwaysByCountries.get(country).add(type);//add the type
                }

            }

            //sort descending by value and take the 10 most common runway identifications
            HashMap<String, Integer> mostCommonRunwayIDS=RunwayIDS.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()).limit(10).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));

            report.setMostCommonRunwayIDS(mostCommonRunwayIDS);
            report.setTypeofRunwaysByCountries(typeOfRunwaysByCountries);
            report.setStatus("Success");

        }catch(IOException e){
            report.setStatus("Server Error");
        }

        return report;

    }

    public static void main(String args[]){

        Report report =new Report();

        try{
            Map<String, Integer> NumberOfAirportsByCountry = new HashMap<String, Integer>();
            Map<Integer, String> AirportsByCountry = new HashMap<Integer, String>();

            Reader in = new FileReader(airportsCSV);
            Iterable<CSVRecord> records=CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) {//read airports csv line

                String country=record.get("iso_country");
                int id=Integer.parseInt(record.get("id"));

                if (NumberOfAirportsByCountry.get(country)==null){
                    NumberOfAirportsByCountry.put(country, 1);
                }else{//increase by one
                    int counter=NumberOfAirportsByCountry.get(country)+1;
                    NumberOfAirportsByCountry.replace(country, counter);
                }

                AirportsByCountry.put(id,country);
            }
            //sort ascending by value and take the 10 first HashMap<String, Integer>
            //String sortedAsc = NumberOfAirportsByCountry.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()).limit(10);
            //sort descending by value and take the 10 first
            Stream<Map.Entry<String, Integer>> sortedDesc = NumberOfAirportsByCountry.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10);
            Stream<Map.Entry<String, Integer>> sortedAsc = NumberOfAirportsByCountry.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()).limit(10);

            System.out.println("top 10 countries by number of airports:");
            sortedDesc.forEach(s -> System.out.println(s));
            System.out.println("bottom: 10 countries by number of airports:");
            sortedAsc.forEach(s -> System.out.println(s));

            //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

            HashMap<String, Integer> RunwayIDS = new HashMap<String, Integer>();
            HashMap<String, List<String> > typeOfRunwaysByCountries = new HashMap<String, List<String> >();

            in = new FileReader(runwaysCSV);
            records=CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) {//read runway csv line

                String RWID = record.get("le_ident");
                String type = record.get("surface");
                int id=Integer.parseInt(record.get("airport_ref"));

                if (RunwayIDS.get(RWID)==null){
                    RunwayIDS.put(RWID, 1);
                }else{
                    int counter = RunwayIDS.get(RWID);
                    counter++;
                    RunwayIDS.replace(RWID, counter);
                }

                //--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*

                String country=AirportsByCountry.get(id);
                //if the country does not exist yet
                if (country!=null && !country.equals("null") &&typeOfRunwaysByCountries.get(country)==null){
                    List<String> types= new ArrayList<String>();
                    types.add(type);
                    typeOfRunwaysByCountries.put(country, types);//create new entry
                }else if(country!=null && !country.equals("null") && !typeOfRunwaysByCountries.get(country).contains(type)){//if the type does not exist for this country
                    typeOfRunwaysByCountries.get(country).add(type);//add the type
                }

            }

            //sort descending by value and take the 10 first
            // 10 most common runway identifications/
            Stream<Map.Entry<String, Integer>> mostCommonRunwayIDS = RunwayIDS.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10);

            System.out.println("mostCommonRunwayIDS:");
            mostCommonRunwayIDS.forEach(s -> System.out.println(s));

            System.out.println("typeOfRunwaysByCountries:"+typeOfRunwaysByCountries.toString());


            //report.setMostCommonRunwayIDS(mostCommonRunwayIDS);
            //report.setTypeofRunwaysbyCountries(typeOfRunwaysByCountries);
            report.setStatus("Success");

        }catch(IOException e){
            report.setStatus("Server Error");
        }


    }


}


