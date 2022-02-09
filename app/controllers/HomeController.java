package controllers;

import handlers.CSVReader;
import models.Report;
import play.mvc.*;
import play.data.*;
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

    public Result getReport() {

        Report report=CSVReader.getReport();

        return ok("Report:"+report.toString());

    }


}
