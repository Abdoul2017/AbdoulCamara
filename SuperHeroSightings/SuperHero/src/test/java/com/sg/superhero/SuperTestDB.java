/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.dao.SuperPersonDao;
import com.sg.superhero.dao.SuperPersonDaoDBImpl;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.SuperPerson;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0200797
 */
public class SuperTestDB {

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

    public SuperTestDB() {

    }
//**************************SUPER PERSON TESTS BEGIN****************************

    private SuperPerson getSuperPerson() {
        System.out.println("TEST GET A SUPER PERSON");
        SuperPerson superPerson = new SuperPerson();

        Organization superOrg = new Organization();

        superOrg.setOrganizationName("The HeroSighting Org");
        superOrg.setOrganizationDescription("Best Sighting Org in Town");
        superOrg.setOrganizationAddress("256 South Main Street");
        superOrg.setOrganizationCity("Madison");
        superOrg.setOrganizationState("Wisconsin");
        superOrg.setOrganizationPhone("2624561845");
        superOrg.setOrganizationEmail("SHSIGHT@LM.com");
        superOrg.setOrganizationZipode("03801");
        //superOrg.setOrganizationId(699);
        superDao.addOrganization(superOrg);

        Location location = new Location();
        location.setLocationName("London");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");
        //location.setLocationId(700);
        superDao.addLocation(location);

        superPerson.setName("Abdoul");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");
        superPerson.setOrg(superOrg);
        superPerson.setLocation(location);

        superPerson.setName("John Boyle");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");
        superPerson.setOrg(superOrg);
        superPerson.setLocation(location);
        //superPerson.setSuperPersonId(50);
        superDao.addSuperPerson(superPerson);

        return superPerson;
    }

    @Test
    public void TestSuperPersonName() {
        System.out.println("TEST SUPER PERSON SHOULD HAVE A NAME");
        SuperPerson superPerson = getSuperPerson();

        String superName = superPerson.getName();

        assertEquals("John Boyle", superName);
    }

    @Test
    public void TestSuperPersonDescription() {
        System.out.println("TEST SUPER PERSON SHOULD HAVE A DESCRIPTION");
        SuperPerson superPerson = getSuperPerson();

        String superDescription = superPerson.getDescription();

        assertEquals("Wolverine", superDescription);
    }

    @Test
    public void TestSuperPersonPower() {
        System.out.println("TEST SUPER PERSON SHOULD HAVE POWER");
        SuperPerson superPerson = getSuperPerson();

        String superPower = superPerson.getSuperPower();

        assertEquals("Precognition", superPower);
    }

    @Test
    public void TestAddSuperPerson() {
        System.out.println("TEST ADD SUPER PERSON");

        //SuperPersonDao superDao = new SuperPersonDaoDBImpl();
        SuperPerson superPerson = new SuperPerson();

        superDao.addSuperPerson(superPerson);

        SuperPerson fromDao = superDao.getSuperPersonById(superPerson.getSuperPersonId());
        assertEquals(superPerson, fromDao);
    }

    @Test
    public void TestGetSuperPersonById() {
        System.out.println("TEST GET SUPER PERSON BY ID");

        SuperPerson superPerson = getSuperPerson();
        superDao.addSuperPerson(superPerson);

        SuperPerson fromDao = superDao.getSuperPersonById(superPerson.getSuperPersonId());
        assertEquals(superPerson.getSuperPersonId(), fromDao.getSuperPersonId());
    }

    @Test
    public void TestSuperPersonByNonExistentId() {
        System.out.println("TEST GET SUPER PERSON BY ID THAT DOESN'T EXIST");
        //Arrange
        SuperPerson superPerson = getSuperPerson();
        superDao.addSuperPerson(superPerson);

        //Act
        SuperPerson fromDao = superDao.getSuperPersonById(77);

        //Assert
        assertNotEquals(superPerson, fromDao);
    }

    @Test
    public void TestSuperPersonNotFound() {
        System.out.println("TEST SUPER PERSON NOT FOUND");
        //Arrange
        int SuperPersonId = 9999;
        SuperPerson superPerson = superDao.getSuperPersonById(SuperPersonId);

        //Assert
        assertNull(superPerson);
    }

    @Test
    public void TestRemoveSuperPersonById() {
        System.out.println("TEST REMOVE SUPER PERSON BY ID");
        //Arrange
        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Killer Keller");
        superPerson.setDescription("Captain America");
        superPerson.setSuperPower("Time Travel");

        //Act
        superDao.addSuperPerson(superPerson);
        SuperPerson fromDao = superDao.getSuperPersonById(superPerson.getSuperPersonId());

        //Assert
        assertEquals(fromDao, superPerson);
        superDao.deleteSuperPerson(superPerson.getSuperPersonId());
        assertNull(superDao.getSuperPersonById(superPerson.getSuperPersonId()));

    }

    @Test
    public void TestGetAllSuperPersons() {
        System.out.println("TEST GET ALL SUPER PERSONS");
        //Arrange
        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Jeff Hiller");
        superPerson.setDescription("SG SuperHero");
        superPerson.setSuperPower("Funny");

        superDao.addSuperPerson(superPerson);

        SuperPerson superPerson2 = new SuperPerson();
        superPerson.setName("Jordan");
        superPerson.setDescription("Fix Computers");
        superPerson.setSuperPower("Virus Injection");

        //ACT
        superDao.addSuperPerson(superPerson2);

        List<SuperPerson> testList = superDao.getAllSuperPersons();

        //Assert
        //assertEquals(testList.size(), 2);
        assertTrue(testList.contains(superPerson));
        assertTrue(testList.contains(superPerson2));
    }

    @Test
    public void TestGetAllSuperPersonsEmptyList() {
        System.out.println("TEST SUPER PERSONS EMPTY LIST");

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Jeff Hiller");
        superPerson.setDescription("SG SuperMan");
        superPerson.setSuperPower("No Need For Sleeping");

        superDao.addSuperPerson(superPerson);

        SuperPerson superPerson2 = new SuperPerson();
        superPerson.setName("Jordan");
        superPerson.setDescription("Fix Computers");
        superPerson.setSuperPower("Virus Injection");

        superDao.addSuperPerson(superPerson2);

        List<SuperPerson> testList = superDao.getAllSuperPersons();

        assertFalse(superDao.getAllSuperPersons().isEmpty());

    }

    @Test
    public void TestUpdateSuperPerson() {
        System.out.println("TEST FOR UPDATE SUPER PERSON");

        SuperPerson superPerson = new SuperPerson();

        superPerson.setName("Christine");
        superPerson.setDescription("SpiderWoman");
        superPerson.setSuperPower("Hard Work and Perseverance");
        superDao.addSuperPerson(superPerson);

        superPerson.setSuperPower("Also like to learn");
        superDao.updateSuperPerson(superPerson);

        SuperPerson fromDao = superDao.getSuperPersonById(superPerson.getSuperPersonId());

        assertEquals(fromDao, superPerson);

    }

    @Test
    public void TestGetAllPersonsByOrganizationId() {
        System.out.println("TEST GET ALL SUPERSON BY ORGANIZATION ID");

        Organization superOrg = new Organization();

        superOrg.setOrganizationName("The HeroSighting Org");
        superOrg.setOrganizationDescription("Best Sighting Org in Town");
        superOrg.setOrganizationAddress("256 South Main Street");
        superOrg.setOrganizationCity("Madison");
        superOrg.setOrganizationState("ILLINOIS");
        superOrg.setOrganizationPhone("2624561845");
        superOrg.setOrganizationEmail("SHSIGHT@LM.com");
        superOrg.setOrganizationZipode("03801");
        //superOrg.setOrganizationId(699);
        superDao.addOrganization(superOrg);

        Location location = new Location();
        location.setLocationName("London");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");
        //location.setLocationId(700);
        superDao.addLocation(location);

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Abdoul");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");
        superPerson.setOrg(superOrg);
        superPerson.setLocation(location);

        superDao.addSuperPerson(superPerson);

        Organization superOrg2 = new Organization();

        superOrg2.setOrganizationName("The HeroSighting Org");
        superOrg2.setOrganizationDescription("Best Sighting Org in Town");
        superOrg2.setOrganizationAddress("256 South Main Street");
        superOrg2.setOrganizationCity("Dirty Dover");
        superOrg2.setOrganizationState("New Hamsphire");
        superOrg2.setOrganizationPhone("2624561845");
        superOrg2.setOrganizationEmail("SHSIGHT90@LM.com");
        superOrg2.setOrganizationZipode("03801");
        //superOrg2.setOrganizationId(899);
        superDao.addOrganization(superOrg2);

        Location location2 = new Location();
        location2.setLocationName("London");
        location2.setLocationDescription("Amazing");
        location2.setLocationLong("30.2");
        location2.setLocationLat("20.4");
        location2.setLocationAddress("15 Parkside Place");
        //location2.setLocationId(333);
        superDao.addLocation(location2);

        SuperPerson superPerson2 = new SuperPerson();
        superPerson2.setName("Abdoul");
        superPerson2.setDescription("Wolverine");
        superPerson2.setSuperPower("Precognition");
        superPerson2.setOrg(superOrg);
        superPerson2.setLocation(location2);

        superDao.addSuperPerson(superPerson2);

        List<SuperPerson> testPersonList = superDao.getSuperPersonByOrganization(superOrg.getOrganizationId());

        assertEquals(testPersonList.size(), 1);
    }

    @Test
    public void TestGetAllPersonsByLocationId() {
        System.out.println("TEST GET ALL SUPER PERSON BY LOCATION ID");

        Organization superOrg = new Organization();

        superOrg.setOrganizationName("The HeroSighting Place");
        superOrg.setOrganizationDescription("Best Sighting Org in Town");
        superOrg.setOrganizationAddress("256 South Main Street");
        superOrg.setOrganizationCity("Madison");
        superOrg.setOrganizationState("Wisconsin");
        superOrg.setOrganizationPhone("2624561845");
        superOrg.setOrganizationEmail("SHSIGHT@LM.com");
        superOrg.setOrganizationZipode("03801");
        //superOrg.setOrganizationId(699);
        superDao.addOrganization(superOrg);

        Location location = new Location();
        location.setLocationName("London");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");
        //location.setLocationId(700);
        superDao.addLocation(location);

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Abdoul");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");
        superPerson.setOrg(superOrg);
        superPerson.setLocation(location);

        superDao.addSuperPerson(superPerson);

        Organization superOrg2 = new Organization();

        superOrg2.setOrganizationName("The HeroSighting Org");
        superOrg2.setOrganizationDescription("Best Sighting Org in Town");
        superOrg2.setOrganizationAddress("256 South Main Street");
        superOrg2.setOrganizationCity("Madison");
        superOrg2.setOrganizationState("Wisconsin");
        superOrg2.setOrganizationPhone("2624561845");
        superOrg2.setOrganizationEmail("SHSIGHT@LM.com");
        superOrg2.setOrganizationZipode("03801");
        //superOrg2.setOrganizationId(899);
        superDao.addOrganization(superOrg2);

        Location location2 = new Location();
        location2.setLocationName("Cedar Rapids");
        location2.setLocationDescription("Amazing");
        location2.setLocationLong("30.2");
        location2.setLocationLat("20.4");
        location2.setLocationAddress("15 Parkside Place");
        //location2.setLocationId(333);
        superDao.addLocation(location2);

        SuperPerson superPerson2 = new SuperPerson();
        superPerson2.setName("Abdoul");
        superPerson2.setDescription("Wolverine");
        superPerson2.setSuperPower("Precognition");
        superPerson2.setOrg(superOrg);
        superPerson2.setLocation(location2);

        superDao.addSuperPerson(superPerson2);

        List<SuperPerson> testPersonList = superDao.getSuperPersonByLocation(location2.getLocationId());

        assertEquals(testPersonList.size(), 1);
    }

}
