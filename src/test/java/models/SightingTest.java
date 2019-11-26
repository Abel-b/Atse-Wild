package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {



    @Test
    public void sightings_returnsRangerNameCorrectly_rangerName(){
        Sighting sighting = new Sighting("Abel", "West", 2);
        String expected = "Abel";
        assertEquals(expected, sighting.getRangerName());
    }

    @Test
    public void sightings_returnsLocationCorrectly_location(){
        Sighting sighting = new Sighting("Abel", "West", 2);
        String expected = "West";
        assertEquals(expected, sighting.getLocation());
    }

    @Test
    public void sightings_returnsAnimalIdCorrectly_AnimalId(){
        Sighting sighting = new Sighting("Abel", "West", 2);
        int expected = 2;
        assertEquals(expected, sighting.getAnimalid());
    }



}