package models;
import org.sql2o.Connection;
import java.util.List;

public class EndangeredAnimal extends Animal {

    private int healthLevel;
    private int age;

    public static final int  HEALTH_STATUS = 10;
    public static final int AGE_RANGE = 10;

    public EndangeredAnimal(String name, int age, int healthLevel) {
        this.name = name;
        this.healthLevel = healthLevel;
        this.age = age;
        endangered = true;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public int getAge() {
        return age;
    }

    public void setHealthLevel(int healthLevel) {
        if(healthLevel < 0 || healthLevel > HEALTH_STATUS)
            throw new IllegalArgumentException("Please enter health Status in a scale of 0-10!");
        this.healthLevel = healthLevel;
    }

    public void setAge(int age) {
        if(age < 0 || age > AGE_RANGE)
            throw new IllegalArgumentException("Please enter age in a scale of 0-10!");
        this.age = age;
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
            if (endangeredAnimal == null){
                throw new IndexOutOfBoundsException("Sorry the animal doesn't exist");
            }
            return endangeredAnimal;
        }
    }
}
