import spark.ModelAndView;
import java.util.ArrayList;
import java.util.HashMap;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/signin", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Signinform.hbs");
        }, new HandlebarsTemplateEngine());
        get("/signup", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Signupform.hbs");
        }, new HandlebarsTemplateEngine());
        get("/contactus", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "contactus.hbs");
        }, new HandlebarsTemplateEngine());
        get("/login", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "login.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
