import models.Sighting;
import models.EndangeredAnimal;
import models.WildAnimal;
import models.Animal;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            req.session().attribute("rangerName", rangerName);
            model.put("rangerName",req.session().attribute("rangerName"));
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> endangeredAnimals=EndangeredAnimal.all();
            model.put("animal",endangeredAnimals);
            model.put("name", request.session().attribute("name"));
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
