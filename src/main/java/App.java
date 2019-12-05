
import com.google.gson.Gson;
import dao.Sql2oGroup;
import models.Group;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.staticFileLocation;
import java.util.ArrayList;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        Sql2oGroup groupDao;
        Connection con;


        String connectionString = "jdbc:postgresql://localhost:5432/ekub";
        Sql2o sql2o = new Sql2o(connectionString, "keith", "1234");





        groupDao = new Sql2oGroup(sql2o);

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

        get("/signin", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "signinform.hbs");
        }, new HandlebarsTemplateEngine());

        get("/group", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            if(groupDao.getAll().size() > 0){
                List<Group> group = groupDao.getAll();
                model.put("group", group);
            } else {
                System.out.println("You have no group added to your ekub db");
            }
            return new ModelAndView(model, "group.hbs");
        }, new HandlebarsTemplateEngine());

        get("/groupForm", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "groupForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/group", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("groupname");
            String round = req.queryParams("groupround");
            int pay = Integer.parseInt(req.queryParams("grouppayment"));
            int size = Integer.parseInt(req.queryParams("groupsize"));
            Group groupNew = new Group(name, size, pay, round);
            groupDao.add(groupNew);
            return new ModelAndView(model, "group.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
