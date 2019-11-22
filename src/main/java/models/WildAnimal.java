package models;

import org.sql2o.Connection;

import java.util.List;

public class WildAnimal extends Animal {

    public WildAnimal(String name) {
        this.name = name;
    }

    public static List<WildAnimal> all(){
        String sql = "SELECT * FROM animals;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(WildAnimal.class);
        }
    }
    public static WildAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            WildAnimal wildAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(WildAnimal.class);
            return wildAnimal;
        }
    }
}
