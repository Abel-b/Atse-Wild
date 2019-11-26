package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {

    @Test
    public void EndangeredAnimal_testIfScaleExceptionOfAgeIsCorrect_fail(){
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Black Lion", 20, 20);
        endangeredAnimal.setAge(12);
        assertEquals(12, endangeredAnimal.getAge());
    }
    @Test
    public void EndangeredAnimal_testIfScaleExceptionOfHealthIsCorrect_fail(){
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Black Lion", 20, 20);
        endangeredAnimal.setHealthLevel(11);
        assertEquals(11, endangeredAnimal.getHealth());
    }


}