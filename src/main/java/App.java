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
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            req.session().attribute("rangerName", rangerName);
            model.put("rangerName", req.session().attribute("rangerName"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.all();
            List<WildAnimal> wildAnimals = WildAnimal.allWild();
            model.put("Eanimal", endangeredAnimals);
            model.put("Wanimal", wildAnimals);
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Eanimals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.all();
            model.put("Eanimal", endangeredAnimals);
            return new ModelAndView(model, "Endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Wanimals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<WildAnimal> wildAnimals = WildAnimal.allWild();
            model.put("Wanimal", wildAnimals);
            return new ModelAndView(model, "WildAnimals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Aform", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animalForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("Aname");
            boolean endangered = req.queryParamsValues("endangered") != null;
            if (endangered) {
                int age = Integer.parseInt(req.queryParams("age"));
                int health = Integer.parseInt(req.queryParams("health"));
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, age, health);
                endangeredAnimal.save();
            } else {
                WildAnimal wildAnimal = new WildAnimal(name);
                wildAnimal.save();
            }
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings = Sighting.all();
            model.put("sight", sightings);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.all();
            model.put("Eanimal", endangeredAnimals);
            List<WildAnimal> wildAnimals = WildAnimal.allWild();
            model.put("Wanimal", wildAnimals);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName");
            String location = request.queryParams("location");
            int animalid = Integer.parseInt(request.queryParams("animalid"));
            Sighting sightings = new Sighting(rangerName, location, animalid);
            sightings.save();
            response.redirect("/sightings");
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
