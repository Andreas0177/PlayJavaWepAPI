package models;

public class Runway {

    String id;

    String type;

    String airportId;

    public Runway(String id, String type, String airportId) {
        this.id = id;
        this.type = type;
        this.airportId = airportId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }
}
