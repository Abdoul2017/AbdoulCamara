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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class LocationTestDB {

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

    public LocationTestDB() {

    }
//******************************************************************************

    private Location getLocation() {
        System.out.println("TEST GET LOCATION");

        Location superLocation = new Location();

        superLocation.setLocationName("Boston");
        superLocation.setLocationDescription("I don't know");
        superLocation.setLocationLong("15");
        superLocation.setLocationLat("8");
        superLocation.setLocationAddress("30 Parkside Place");
        //superLocation.setLocationId(90);
        superDao.addLocation(superLocation);

        return superLocation;
    }

    @Test
    public void TestLocationName() {
        System.out.println("TEST LOCATION NAME");
        Location superLocal = getLocation();

        String LocationName = superLocal.getLocationName();

        assertEquals("Boston", LocationName);
    }

    @Test
    public void TestSuperPersonDescription() {
        System.out.println("TEST SUPER PERSON SHOULD HAVE A DESCRIPTION");
        Location superLocal = getLocation();

        String LocationDescription = superLocal.getLocationDescription();

        assertEquals("I don't know", LocationDescription);
    }

    @Test
    public void TestLocationLongitude() {
        System.out.println("TEST LOCATION LONGITUDE");

        Location superLocal = getLocation();

        String LocationLong = superLocal.getLocationLong();

        assertEquals("15", LocationLong);
    }

    @Test
    public void TestLocationLatitude() {
        System.out.println("TEST LOCATION LATITUDE");

        Location superLocal = getLocation();

        String LocationLat = superLocal.getLocationLat();

        assertEquals("8", LocationLat);
    }

    @Test
    public void TestLocationAddress() {
        System.out.println("TEST LOCATION ADDRESS");

        Location superLocal = getLocation();

        String LocationAddress = superLocal.getLocationAddress();

        assertEquals("30 Parkside Place", LocationAddress);
    }

    @Test
    public void TestAddLocation() {
        System.out.println("TEST ADD LOCATION");
        //SuperPersonDao superDao = new SuperPersonDaoDBImpl();
        //SuperPersonDao superDao = new SuperPersonDaoTestImpl();

        Location superLocal = new Location();
        superLocal.setLocationName("Excellent");
        superLocal.setLocationDescription("Bull Shit Location");
        superLocal.setLocationLong("33.2");
        superLocal.setLocationLat("12.34");
        superLocal.setLocationAddress("246 West Morgan");

        superDao.addLocation(superLocal);

        Location fromDao = superDao.getLocationByID(superLocal.getLocationId());
        assertEquals(superLocal, fromDao);
    }

    @Test
    public void TestGetLocationById() {
        System.out.println("TEST GET LOCATION BY ID");

        Location superLocal = getLocation();
        superDao.addLocation(superLocal);

        Location fromDao = superDao.getLocationByID(superLocal.getLocationId());
        assertEquals(superLocal, fromDao);
    }

    @Test
    public void TestLocationByNonExistentId() {
        System.out.println("TEST GET LOCATION BY ID THAT DOESN'T EXIST");
        //Arrange
        Location superLocal = getLocation();
        superDao.addLocation(superLocal);

        //Act
        Location fromDao = superDao.getLocationByID(101);

        //Assert
        assertNotEquals(superLocal, fromDao);
    }

    @Test
    public void TestLocationNotFound() {
        System.out.println("TEST LOCATION NOT FOUND");
        //Arrange
        int LocationId = 8888;
        Location superLocal = superDao.getLocationByID(LocationId);

        //Assert
        assertNull(superLocal);
    }

    @Test
    public void TestRemoveLocationById() {
        System.out.println("TEST REMOVE LOCATION BY ID");

        Location superLocal = new Location();
        superLocal.setLocationName("Chicago");
        superLocal.setLocationDescription("Dangerous Location");
        superLocal.setLocationLong("10.2");
        superLocal.setLocationLat("4.5");
        superLocal.setLocationAddress("154 Main Street");

        superDao.addLocation(superLocal);
        Location fromDao = superDao.getLocationByID(superLocal.getLocationId());

        assertEquals(fromDao, superLocal);
        superDao.deleteLocation(superLocal.getLocationId());
        assertNull(superDao.getLocationByID(superLocal.getLocationId()));

    }

    @Test
    public void TestGetAllLocations() {
        System.out.println("TEST GET ALL LOCATIONS");

        Location superLocal = new Location();

        superLocal.setLocationName("Durham");
        superLocal.setLocationDescription("Ok Location");
        superLocal.setLocationLong("40.2");
        superLocal.setLocationLat("22.5");
        superLocal.setLocationAddress("230 West Arlington");

        superDao.addLocation(superLocal);

        Location superLocal2 = new Location();
        superLocal2.setLocationName("Newmarket");
        superLocal2.setLocationDescription("Bad Location");
        superLocal2.setLocationLong("80.2");
        superLocal2.setLocationLat("12.5");
        superLocal2.setLocationAddress("96 East Canal");

        superDao.addLocation(superLocal2);

        List<Location> testLocationList = superDao.getAllLocations();

        assertEquals(testLocationList.size(), 2);
    }

    @Test
    public void TestForAllLocationEmptyList() {
        System.out.println("TEST LOCATION LIST NOT EMPTY");

        Location superLocal = new Location();

        superLocal.setLocationName("Durham");
        superLocal.setLocationDescription("Ok Location");
        superLocal.setLocationLong("40.2");
        superLocal.setLocationLat("22.5");
        superLocal.setLocationAddress("230 West Arlington");

        superDao.addLocation(superLocal);

        Location superLocal2 = new Location();
        superLocal2.setLocationName("Newmarket");
        superLocal2.setLocationDescription("Bad Location");
        superLocal2.setLocationLong("80.2");
        superLocal2.setLocationLat("12.5");
        superLocal2.setLocationAddress("96 East Canal");

        superDao.addLocation(superLocal2);

        List<Location> testLocationList = superDao.getAllLocations();

        assertFalse(superDao.getAllLocations().isEmpty());
    }

    @Test
    public void TestUpdateLocation() {
        System.out.println("TEST FOR UPDATE LOCATION");
        Location superLocal = new Location();

        superLocal.setLocationName("Dover");
        superLocal.setLocationDescription("Best Location");
        superLocal.setLocationLong("32.4");
        superLocal.setLocationLat("26.3");
        superLocal.setLocationAddress("22 Crown Point Drive");

        superDao.addLocation(superLocal);

        superLocal.setLocationAddress("22 Charles Johnson Avenue");
        superDao.updateLocation(superLocal);

        Location fromDao = superDao.getLocationByID(superLocal.getLocationId());

        assertEquals(fromDao, superLocal);
    }

}
