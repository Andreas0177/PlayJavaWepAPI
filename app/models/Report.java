package models;

import java.util.HashMap;
import java.util.List;

public class Report{
    HashMap<String, Integer> top10;
    HashMap<String, Integer> bottom10;
    HashMap<String, Integer> mostCommonRunwayIDS;
    HashMap<String, List<String>> typeofRunwaysByCountries;
    String status;

    @Override
    public String toString() {
        return "Report{" +
                "top10=" + top10 +
                ", bottom10=" + bottom10 +
                ", mostCommonRunwayIDS=" + mostCommonRunwayIDS +
                ", typeofRunwaysByCountries=" + typeofRunwaysByCountries +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<String, Integer> getTop10() {
        return top10;
    }

    public void setTop10(HashMap<String, Integer> top10) {
        this.top10 = top10;
    }

    public HashMap<String, Integer> getBottom10() {
        return bottom10;
    }

    public void setBottom10(HashMap<String, Integer> bottom10) {
        this.bottom10 = bottom10;
    }

    public HashMap<String, Integer> getMostCommonRunwayIDS() {
        return mostCommonRunwayIDS;
    }

    public void setMostCommonRunwayIDS(HashMap<String, Integer> mostCommonRunwayIDS) {
        this.mostCommonRunwayIDS = mostCommonRunwayIDS;
    }

    public HashMap<String, List<String> > getTypeofRunwaysByCountries() {
        return typeofRunwaysByCountries;
    }

    public void setTypeofRunwaysByCountries(HashMap<String, List<String> > typeofRunwaysByCountries) {
        this.typeofRunwaysByCountries = typeofRunwaysByCountries;
    }
}