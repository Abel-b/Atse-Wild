package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Sighting {
    private String rangerName;
    private String location;
    private int animalId;
    private int id;

    public Sighting(String rangerName, String location) {
        this.rangerName = rangerName;
        this.location = location;
        this.animalId = animalId;
    }

    public String getRangerName() {
        return rangerName;
    }

    public String getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (rangerName, animalid, location) VALUES (:rangerName, :animalId, :location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }
    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            if(sighting == null){
                throw new IndexOutOfBoundsException("I'm sorry, I think this sighting does not exist");
            }
            return sighting;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return getId() == sighting.getId() &&
                Objects.equals(getRangerName(), sighting.getRangerName()) &&
                Objects.equals(getLocation(), sighting.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRangerName(), getLocation(), getId());
    }
}
