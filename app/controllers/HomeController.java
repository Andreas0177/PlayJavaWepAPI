package controllers;

import handlers.CSVReader;
import models.Airport;
import models.Report;
import play.mvc.*;
import play.data.*;
import repositories.LocalCSVRepo;
import services.QueryService;

import javax.inject.*;
import java.util.HashMap;
import java.util.List;

public class HomeController extends Controller {

    @Inject FormFactory formFactory;


    public Result explore() {
        return ok(views.html.explore.render());
    }

    public Result tutorial() {
        return ok(views.html.tutorial.render());
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result query() {
        return ok(views.html.queryform.render());
    }


    public Result result(Http.Request request) {
        DynamicForm requestData = formFactory.form().bindFromRequest(request);
        String input = requestData.get("query");
        HashMap<String, List<String>> out=CSVReader.getAirPortsOfSelectedCountry(input);
        return ok("AirPortsOfSelectedCountry:"+out);

    }

    public Result result2(Http.Request request) {
        DynamicForm requestData = formFactory.form().bindFromRequest(request);
        List<Airport> airports = QueryService.getQueryResult(new LocalCSVRepo(), requestData.get("query"));//new LocalCSVRepo() is wrong it must be done with injection
        return ok("AirPortsOfSelectedCountry:"+airports);
    }

    public Result getReport() {

        Report report=CSVReader.getReport();

        return ok("Report:"+report.toString());

    }


}
