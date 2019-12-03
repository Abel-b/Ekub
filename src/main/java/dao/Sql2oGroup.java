package dao;


import models.Group;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oGroup implements GroupDao {

    private final Sql2o sql2o;

    public Sql2oGroup(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Group group) {
        String sql = "INSERT INTO grouptable(groupname, groupsize, groupround, grouppayment) VALUES (:groupname, :groupsize, :groupround, :grouppayment)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(group)
                    .executeUpdate()
                    .getKey();
            group.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Group> getAll() {
        String sql = "SELECT * FROM grouptable;";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Group.class);
        }
    }
}
