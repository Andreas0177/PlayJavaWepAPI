package models;

import java.util.ArrayList;
import java.util.List;

public class Airport {

    String name;
    String id;
    String country;

    List<Runway> runwayList;

    public Airport(String name, String id, String country) {
        this.name = name;
        this.id = id;
        this.country = country;
        runwayList=new ArrayList<Runway>();
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", runwayList=" + runwayList +
                '}';
    }

    public List<Runway> getRunwayList() {
        return runwayList;
    }

    public void setRunwayList(List<Runway> runwayList) {
        this.runwayList = runwayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
