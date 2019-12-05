
import com.google.gson.Gson;
import dao.DB;
import dao.Sql2oGroup;
import dao.Sql2oUserDao;
import models.Group;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static spark.Spark.staticFileLocation;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        Sql2oGroup groupDao;
        Sql2oUserDao sql2oUserDao;
        Connection con;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/ekub";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

        groupDao = new Sql2oGroup(sql2o);
        sql2oUserDao = new Sql2oUserDao(DB.sql2o);

        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/signin", "text/html", (request, response) -> {
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

        //register user
        post("/signup", (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            String username = request.queryParams("username");
            String email = request.queryParams("email");
            int phonenumber = Integer.parseInt(request.queryParams("phonenumber"));
            String passwordString = request.queryParams("password").trim();

            //hash the password here
            String password = encryptPassword(passwordString);

            User userToRegister = new User(username, email, phonenumber, password);
            sql2oUserDao.add(userToRegister);
            if (String.valueOf(response.status()).startsWith(String.valueOf(2))) {
                System.out.println("Account Created Successfully");
                response.redirect("/signin");
                return null;
            } else {
                map.put("error", response.body());
                System.out.println(response.status());
                return new ModelAndView(map, "Signinform.hbs");
            }
        }, new HandlebarsTemplateEngine());

        //login user
        post("/signin", (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            String email = request.queryParams("email").trim();
            String password = request.queryParams("password").trim();

            User user = sql2oUserDao.getUserByEmail(email);
            if (user == null) {
                response.redirect("/signin");
                return null;
            } else {
                if (user.getPassword().equals(encryptPassword(password))) {
                    System.out.println(response.body());
                    response.redirect("/");
                    return null;
                } else {
                    Map<String, Object> hashMap = new HashMap<>();
                    hashMap.put("result", "Invalid Credentials. Try again!");
                    System.out.println("Thats an error");
                    return new ModelAndView(hashMap, "Signinform.hbs");
                }
            }
        }, new HandlebarsTemplateEngine());


        get("/group", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            if (groupDao.getAll().size() > 0) {
                List<Group> group = groupDao.getAll();
                model.put("group", group);
            } else {
                System.out.println("You have no group added to your ekub db");
            }
            return new ModelAndView(model, "group.hbs");
        }, new HandlebarsTemplateEngine());

    }

    //encrypt user password with sha-512 message digest
    private static String encryptPassword(String passwordString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] messageDigestResult = messageDigest.digest(passwordString.getBytes());

            BigInteger signNum = new BigInteger(1, messageDigestResult);
            String hashText = signNum.toString(16);

            while (hashText.length() < 32) {
                hashText = "0".concat(hashText);
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
