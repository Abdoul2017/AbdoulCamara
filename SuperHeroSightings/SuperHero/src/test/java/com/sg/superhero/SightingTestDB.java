/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.dao.SuperPersonDao;
import com.sg.superhero.dao.SuperPersonDaoDBImpl;
import com.sg.superhero.dao.SuperPersonDaoTestImpl;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SuperPerson;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0200797
 */
public class SightingTestDB {
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @org.junit.Before
    public void setUp() throws Exception {

        superDao = new SuperPersonDaoDBImpl();
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        superDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
    }

    @After
    public void tearDown() {
    }

//******************************************************************************    
    SuperPersonDao superDao;

    public SightingTestDB() {

    }
//*****************************SIGHTING TESTS BEGIN*****************************

    private Sighting getSighting() {
        System.out.println("TEST GET SIGHTING");
        Sighting superSight = new Sighting();

        Location location = new Location();
        location.setLocationName("Boston");
        location.setLocationDescription("I don't know");
        location.setLocationLong("15");
        location.setLocationLat("8");
        location.setLocationAddress("30 Parkside Place");
        location.setLocationId(90);

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("John Boyle");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");
        superPerson.setSuperPersonId(98);

        superSight.setSightingDate(LocalDate.of(2015, Month.MARCH, 15));

        superSight.setLocation(location);
        superSight.setSuperPerson(superPerson);
        superSight.setSightingId(666);

        return superSight;
    }

    @Test
    public void TestSightingDate() {
        System.out.println("TEST SIGHTING DATE");
        Sighting superSight = getSighting();

        LocalDate superDate = superSight.getSightingDate();

        assertEquals(LocalDate.of(2015, Month.MARCH, 15), superDate);
    }

    @Test
    public void TestSightingLocation() {
        System.out.println("TEST SIGHTING LOCATION");
        Sighting superSight = getSighting();

        String superSightLocation = superSight.getLocation().getLocationName();
        String superSightDescription = superSight.getLocation().getLocationDescription();
        String superSightLong = superSight.getLocation().getLocationLong();
        String superSightLat = superSight.getLocation().getLocationLat();
        String superSightAddress = superSight.getLocation().getLocationAddress();

        assertEquals("Boston", superSightLocation);
        assertEquals("I don't know", superSightDescription);
        assertEquals("15", superSightLong);
        assertEquals("8", superSightLat);
        assertEquals("30 Parkside Place", superSightAddress);
    }

    @Test
    public void TestSightingPersons() {
        System.out.println("TEST SIGHTING PERSONS");
        Sighting superSight = getSighting();

        String superSightName = superSight.getSuperPerson().getName();
        String superSightDescription = superSight.getSuperPerson().getDescription();
        String superSightSuperPower = superSight.getSuperPerson().getSuperPower();

        assertEquals("John Boyle", superSightName);
        assertEquals("Wolverine", superSightDescription);
        assertEquals("Precognition", superSightSuperPower);

    }

    @Test
    public void TestAddSighting() {
        System.out.println("TEST ADD SIGHTING");

        SuperPersonDao superDao = new SuperPersonDaoTestImpl();
        Sighting superSight = new Sighting();

        superDao.addSighting(superSight);

        Sighting fromDao = superDao.getSightingByID(superSight.getSightingId());
        assertEquals(superSight, fromDao);
    }

    @Test
    public void TestGetSightingById() {
        System.out.println("TEST SIGHTING BY ID");

        Sighting superSight = getSighting();
        superDao.addSighting(superSight);

        Sighting fromDao = superDao.getSightingByID(superSight.getSightingId());
        assertEquals(superSight, fromDao);
    }

    @Test
    public void TestSightingByNonExistentId() {
        System.out.println("TEST GET SIGHTING BY ID THAT DOESN'T EXIST");
        //Arrange
        Sighting superSight = getSighting();
        superDao.addSighting(superSight);

        //Act
        Sighting fromDao = superDao.getSightingByID(89);

        //Assert
        assertNotEquals(superSight, fromDao);
    }

    @Test
    public void TestSuperPersonNotFound() {
        System.out.println("TEST SIGHTING ID NOT FOUND");

        int SightingId = 5099;
        Sighting superSight = superDao.getSightingByID(SightingId);

        assertNull(superSight);
    }

    @Test
    public void TestRemoveSighting() {
        System.out.println("TEST REMOVE SIGHTING BY ID");

        Sighting superSight = new Sighting();
        Location location = new Location();
        location.setLocationName("London");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");
        superDao.addLocation(location);

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Abdoul");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");

        superSight.setSightingDate(LocalDate.of(2017, Month.MARCH, 30));
        superSight.getLocation().getLocationId();
        superSight.setSuperPerson(superPerson);

        superDao.addSighting(superSight);
        Sighting fromDao = superDao.getSightingByID(superSight.getSightingId());

        assertEquals(fromDao, superSight);
        superDao.deleteSighting(superSight.getSightingId());
        assertNull(superDao.getSightingByID(superSight.getSightingId()));
    }

    @Test
    public void TestUpdateSighting() {
        System.out.println("TEST FOR UPDATE SIGHTING");
        Sighting superSight = new Sighting();
        Location location = new Location();
        location.setLocationName("Lyon");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Abdoul");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");

        superSight.setSightingDate(LocalDate.of(2017, Month.MARCH, 30));
        superSight.setLocation(location);
        superSight.setSuperPerson(superPerson);

        superDao.addSighting(superSight);

        superSight.setSightingDate(LocalDate.of(2018, Month.APRIL, 30));
        superDao.updateSighting(superSight);

        Sighting fromDao = superDao.getSightingByID(superSight.getSightingId());

        assertEquals(fromDao, superSight);

    }

    @Test
    public void TestGetAllSightings() {
        System.out.println("TEST GET ALL SIGHTINGS");
        Sighting superSight = new Sighting();
        Location location = new Location();
        location.setLocationName("London");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Abdoul");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");

        superSight.setSightingDate(LocalDate.of(2017, Month.MARCH, 30));
        superSight.setLocation(location);
        superSight.setSuperPerson(superPerson);

        superDao.addSighting(superSight);

        Sighting superSight2 = new Sighting();
        Location location2 = new Location();
        location2.setLocationName("NoWHEre");
        location2.setLocationDescription("Bad");
        location2.setLocationLong("10.2");
        location2.setLocationLat("80.4");
        location2.setLocationAddress("30 Parkside Place");

        SuperPerson superPerson2 = new SuperPerson();
        superPerson2.setName("Abdoulaye");
        superPerson2.setDescription("Wolverine");
        superPerson2.setSuperPower("Precognition");

        superSight2.setSightingDate(LocalDate.of(2015, Month.AUGUST, 4));
        superSight2.setLocation(location);
        superSight2.setSuperPerson(superPerson);

        superDao.addSighting(superSight2);

        List<Sighting> testSightingList = superDao.getAllSightings();

        assertEquals(testSightingList.size(), 2);
    }

    @Test
    public void TestGetAllSightingsByDate() {
        System.out.println("TEST GET ALL SIGHTINGS BY DATE");
        Sighting superSight = new Sighting();
        Location location = new Location();
        location.setLocationName("London");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Abdoul");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");

        superSight.setSightingDate(LocalDate.of(2017, Month.MARCH, 30));
        superSight.setLocation(location);
        superSight.setSuperPerson(superPerson);

        superDao.addSighting(superSight);

        Sighting superSight2 = new Sighting();
        Location location2 = new Location();
        location2.setLocationName("NoWHEre");
        location2.setLocationDescription("Bad");
        location2.setLocationLong("10.2");
        location2.setLocationLat("80.4");
        location2.setLocationAddress("30 Parkside Place");

        SuperPerson superPerson2 = new SuperPerson();
        superPerson2.setName("Abdoulaye");
        superPerson2.setDescription("Wolverine");
        superPerson2.setSuperPower("Precognition");

        superSight2.setSightingDate(LocalDate.of(2015, Month.AUGUST, 4));
        superSight2.setLocation(location2);
        superSight2.setSuperPerson(superPerson2);

        superDao.addSighting(superSight2);

        List<Sighting> testSightingList = superDao.getSightingsByDate(LocalDate.of(2017, Month.MARCH, 30));

        assertEquals(testSightingList.size(), 1);
    }
    
       @Test
    public void TestGetAllSightingsByLocationId() {
        System.out.println("TEST GET ALL SIGHTINGS BY DATE");
        Sighting superSight = new Sighting();
        Location location = new Location();
        location.setLocationName("London");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");
        location.setLocationId(800);

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Abdoul");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");

        superSight.setSightingDate(LocalDate.of(2017, Month.MARCH, 30));
        superSight.setLocation(location);
        superSight.setSuperPerson(superPerson);

        superDao.addSighting(superSight);

        Sighting superSight2 = new Sighting();
        Location location2 = new Location();
        location2.setLocationName("NoWHEre");
        location2.setLocationDescription("Bad");
        location2.setLocationLong("10.2");
        location2.setLocationLat("80.4");
        location2.setLocationAddress("30 Parkside Place");
        location.setLocationId(900);

        SuperPerson superPerson2 = new SuperPerson();
        superPerson2.setName("Abdoulaye");
        superPerson2.setDescription("Wolverine");
        superPerson2.setSuperPower("Precognition");

        superSight2.setSightingDate(LocalDate.of(2015, Month.AUGUST, 4));
        superSight2.setLocation(location2);
        superSight2.setSuperPerson(superPerson2);

        superDao.addSighting(superSight2);

        List<Sighting> testSightingList = superDao.getSightingsByLocationId(900);

        assertEquals(testSightingList.size(), 1);
    }
    
    
}
