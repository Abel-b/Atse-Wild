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
            List<WildAnimal> wildAnimals=WildAnimal.allWild();
            model.put("Eanimal",endangeredAnimals);
            model.put("Wanimal",wildAnimals);
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Eanimals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> endangeredAnimals=EndangeredAnimal.all();
            model.put("Eanimal",endangeredAnimals);
            return new ModelAndView(model,"Endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Wanimals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<WildAnimal> wildAnimals=WildAnimal.allWild();
            model.put("Wanimal",wildAnimals);
            return new ModelAndView(model,"WildAnimals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Aform", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"animalForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("Aname");
            boolean endangered = req.queryParamsValues("endangered")!=null;
            if(endangered){
                Integer age = Integer.parseInt(req.queryParams("age"));
                Integer health = Integer.parseInt(req.queryParams("health"));
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name,age, health);
                endangeredAnimal.save();
            } else {
                WildAnimal wildAnimal = new WildAnimal(name);
                wildAnimal.save();
            }
            return new ModelAndView(model,"animals.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
