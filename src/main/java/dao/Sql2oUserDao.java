package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oUserDao implements UserDao {

    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = DB.sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO usertable(username,email,phonenumber,password) VALUES(:username,:email,:phonenumber,:password)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM usertable WHERE email=:email";
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("email", email)
                    .executeAndFetchFirst(User.class);
        }
    }

}
