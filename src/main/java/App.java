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

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {

    public static void main(String[] args) {

        Sql2oGroup groupDao;
        Connection con;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/ekub";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "berhane1234");


        groupDao = new Sql2oGroup(sql2o);

        staticFileLocation("/public");

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

    }
}
