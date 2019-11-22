package models;
import org.sql2o.Connection;
import java.util.List;

public class EndangeredAnimal extends Animal {

    public EndangeredAnimal(String name) {
        this.name = name;
    }

    public static List<EndangeredAnimal> all(){
        String sql = "SELECT * FROM animals;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }
    public static EndangeredAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            EndangeredAnimal endangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredAnimal;
        }
    }
}
