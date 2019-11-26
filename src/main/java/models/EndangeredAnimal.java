package models;
import org.sql2o.Connection;
import java.util.List;

public class EndangeredAnimal extends Animal {

    private int health;
    private int age;

    public static final int  HEALTH_STATUS = 10;
    public static final int AGE_RANGE = 10;

    public EndangeredAnimal(String name, int age, int health) {
        this.name = name;
        this.health = HEALTH_STATUS;
        this.age = AGE_RANGE;
        endangered = true;
    }

    public int getHealth() {
        return health;
    }

    public int getAge() {
        return age;
    }

    public void setHealthLevel(int healthLevel) {
        if(healthLevel < 0 || healthLevel > HEALTH_STATUS)
            throw new IllegalArgumentException("Please enter health Status in a scale of 0-10!");
        this.health = healthLevel;
    }

    public void setAge(int age) {
        if(age < 0 || age > AGE_RANGE)
            throw new IllegalArgumentException("Please enter age in a scale of 0-10!");
        this.age = age;
    }

    public static List<EndangeredAnimal> all(){
        String sql = "SELECT * FROM animals WHERE endangered = 'true';";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
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
    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, endangered, health, age) VALUES (:name, :endangered, :health, :age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("endangered", this.endangered)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
        }
    }
}
