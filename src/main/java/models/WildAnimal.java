package models;

import org.sql2o.Connection;

import java.util.List;

public class WildAnimal extends Animal {

    public WildAnimal(String name) {
        this.name = name;
        endangered = false;
    }

    public static List<WildAnimal> all(){
        String sql = "SELECT * FROM animals WHERE endangered = 'false';";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(WildAnimal.class);
        }
    }
    public static WildAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            WildAnimal wildAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(WildAnimal.class);
            if(wildAnimal == null ){
                throw new IndexOutOfBoundsException("Sorry, the animal doesn't exist");
            }
            return wildAnimal;
        }
    }
}
