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
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.SuperPerson;
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
public class OrganizationTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @org.junit.Before
    public void setUp() throws Exception {
        superDao = new SuperPersonDaoTestImpl();
//        superDao = new SuperPersonDaoDBImpl();
//        
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        superDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
    }

    @After
    public void tearDown() {
    }

//******************************************************************************    
    SuperPersonDao superDao;

    public OrganizationTest() {

    }
//**************************ORGANIZATION TESTS BEGIN****************************

    private Organization getOrganization() {
        System.out.println("TEST GET ORGANIZATION");

        Organization superOrg = new Organization();

        superOrg.setOrganizationName("The HeroSighting Org");
        superOrg.setOrganizationDescription("Best Sighting Org in Town");
        superOrg.setOrganizationAddress("256 South Main Street");
        superOrg.setOrganizationCity("Madison");
        superOrg.setOrganizationState("Wisconsin");
        superOrg.setOrganizationPhone("2624561845");
        superOrg.setOrganizationEmail("SHSIGHT@LM.com");
        superOrg.setOrganizationZipode("03801");
        superOrg.setOrganizationId(115);

        return superOrg;
    }

    @Test
    public void TestOrganizationName() {
        System.out.println("TEST FOR ORGANIZATION NAME");
        Organization superOrg = getOrganization();

        String OrgName = superOrg.getOrganizationName();

        assertEquals("The HeroSighting Org", OrgName);
    }

    @Test
    public void TestOrganizationDescription() {
        System.out.println("TEST FOR ORGANIZATION DESCRIPTION");
        Organization superOrg = getOrganization();

        String OrgDescription = superOrg.getOrganizationDescription();

        assertEquals("Best Sighting Org in Town", OrgDescription);
    }

    @Test
    public void TestOrganizationAddress() {
        System.out.println("TEST FOR ORGANIZATION ADDRESS");
        Organization superOrg = getOrganization();

        String OrgAddress = superOrg.getOrganizationAddress();

        assertEquals("256 South Main Street", OrgAddress);
    }

    @Test
    public void TestOrganizationCity() {
        System.out.println("TEST FOR ORGANIZATION CITY");
        Organization superOrg = getOrganization();

        String OrgCity = superOrg.getOrganizationCity();

        assertEquals("Madison", OrgCity);
    }

    @Test
    public void TestOrganizationState() {
        System.out.println("TEST FOR ORGANIZATION STATE");
        Organization superOrg = getOrganization();

        String OrgState = superOrg.getOrganizationState();

        assertEquals("Wisconsin", OrgState);
    }

    @Test
    public void TestOrganizationPhone() {
        System.out.println("TEST FOR ORGANIZATION PHONE");
        Organization superOrg = getOrganization();

        String OrgPhone = superOrg.getOrganizationPhone();

        assertEquals("2624561845", OrgPhone);
    }

    @Test
    public void TestOrganizationEmail() {
        System.out.println("TEST FOR ORGANIZATION EMAIL");
        Organization superOrg = getOrganization();

        String OrgEmail = superOrg.getOrganizationEmail();

        assertEquals("SHSIGHT@LM.com", OrgEmail);
    }

    @Test
    public void TestOrganizationZipCode() {
        System.out.println("TEST FOR ORGANIZATION ZipCode");
        Organization superOrg = getOrganization();

        String OrgZipCode = superOrg.getOrganizationZipode();

        assertEquals("03801", OrgZipCode);
    }

    @Test
    public void TestAddOrganization() {
        System.out.println("TEST ADD ORGANIZATION");

        SuperPersonDao superDao = new SuperPersonDaoTestImpl();
        Organization superOrg = new Organization();

        superDao.addOrganization(superOrg);

        Organization fromDao = superDao.getOrganizationById(superOrg.getOrganizationId());
        assertEquals(superOrg, fromDao);
    }

    @Test
    public void TestGetOrganizationById() {
        System.out.println("TEST GET ORGANIZATION BY ID");

        Organization superOrg = getOrganization();
        superDao.addOrganization(superOrg);

        Organization fromDao = superDao.getOrganizationById(superOrg.getOrganizationId());
        assertEquals(superOrg, fromDao);
    }

    @Test
    public void TestOrganizationNonExistentId() {
        System.out.println("TEST GET ORGANIZATION BY ID THAT DOESN'T EXIST");
        //Arrange
        Organization superOrg = getOrganization();
        superDao.addOrganization(superOrg);

        //Act
        Organization fromDao = superDao.getOrganizationById(230);

        //Assert
        assertNotEquals(superOrg, fromDao);
    }

    @Test
    public void TestOrganizationNotFound() {
        System.out.println("TEST ORGANIZATION NOT FOUND");

        int OrganizationId = 7777;
        Organization superOrg = superDao.getOrganizationById(OrganizationId);

        assertNull(superOrg);
    }

    @Test
    public void TestRemoveOrganizationById() {
        System.out.println("TEST REMOVE ORGANIZATION BY ID");
        //Arrange
        Organization superOrg = new Organization();

        superOrg.setOrganizationName("SuperHero Land");
        superOrg.setOrganizationDescription("Super Sighting and Power");
        superOrg.setOrganizationAddress("256 Pleasant Street");
        superOrg.setOrganizationCity("Portsmouth");
        superOrg.setOrganizationState("New Hamsphire");
        superOrg.setOrganizationPhone("2624561846");
        superOrg.setOrganizationEmail("SHSIGHT225@LM.com");
        superOrg.setOrganizationZipode("02148");

        //Act
        superDao.addOrganization(superOrg);
        Organization fromDao = superDao.getOrganizationById(superOrg.getOrganizationId());

        //Assert
        assertEquals(fromDao, superOrg);
        superDao.deleteOrganization(superOrg.getOrganizationId());
        assertNull(superDao.getOrganizationById(superOrg.getOrganizationId()));
    }

    @Test
    public void TestGetAllOrganizations() {
        System.out.println("TEST GET ALL ORGANIZATION");
        //Arrange
        Organization superOrg = new Organization();

        superOrg.setOrganizationName("SuperHero Land");
        superOrg.setOrganizationDescription("Super Sighting and Power");
        superOrg.setOrganizationAddress("256 Pleasant Street");
        superOrg.setOrganizationCity("Portsmouth");
        superOrg.setOrganizationState("New Hamsphire");
        superOrg.setOrganizationPhone("2624561846");
        superOrg.setOrganizationEmail("SHSIGHT225@LM.com");
        superOrg.setOrganizationZipode("02148");

        superDao.addOrganization(superOrg);

        Organization superOrg2 = new Organization();

        superOrg2.setOrganizationName("SuperHero Land");
        superOrg2.setOrganizationDescription("Super Sighting and Power");
        superOrg2.setOrganizationAddress("256 Pleasant Street");
        superOrg2.setOrganizationCity("Portsmouth");
        superOrg2.setOrganizationState("New Hamsphire");
        superOrg2.setOrganizationPhone("2624561846");
        superOrg2.setOrganizationEmail("SHSIGHT225@LM.com");
        superOrg2.setOrganizationZipode("02148");

        //ACT
        superDao.addOrganization(superOrg2);

        List<Organization> testOrgList = superDao.getAllOrganizations();

        //Assert
        assertEquals(testOrgList.size(), 2);
    }

    @Test
    public void TestGetAllOrganizationsEmptyList() {
        System.out.println("TEST ORGANIZATION LIST IS NOT EMPTY");

        Organization superOrg = new Organization();

        superOrg.setOrganizationName("SuperHero Land");
        superOrg.setOrganizationDescription("Super Sighting and Power");
        superOrg.setOrganizationAddress("256 Pleasant Street");
        superOrg.setOrganizationCity("Portsmouth");
        superOrg.setOrganizationState("New Hamsphire");
        superOrg.setOrganizationPhone("2624561846");
        superOrg.setOrganizationEmail("SHSIGHT225@LM.com");
        superOrg.setOrganizationZipode("02148");

        superDao.addOrganization(superOrg);

        Organization superOrg2 = new Organization();

        superOrg2.setOrganizationName("SuperHero Land");
        superOrg2.setOrganizationDescription("Super Sighting and Power");
        superOrg2.setOrganizationAddress("256 Pleasant Street");
        superOrg2.setOrganizationCity("Portsmouth");
        superOrg2.setOrganizationState("New Hamsphire");
        superOrg2.setOrganizationPhone("2624561846");
        superOrg2.setOrganizationEmail("SHSIGHT225@LM.com");
        superOrg2.setOrganizationZipode("02148");

        //ACT
        superDao.addOrganization(superOrg2);

        List<Organization> testOrgList = superDao.getAllOrganizations();

        assertFalse(superDao.getAllOrganizations().isEmpty());
    }

    @Test
    public void TestUpdateOrganization() {
        System.out.println("TEST FOR UPDATE ORGANIZATION");

        Organization superOrg = new Organization();

        superOrg.setOrganizationName("SuperHero Land");
        superOrg.setOrganizationDescription("Super Sighting and Power");
        superOrg.setOrganizationAddress("256 Pleasant Street");
        superOrg.setOrganizationCity("Portsmouth");
        superOrg.setOrganizationState("New Hamsphire");
        superOrg.setOrganizationPhone("2624561846");
        superOrg.setOrganizationEmail("SHSIGHT225@LM.com");
        superOrg.setOrganizationZipode("02148");

        superOrg.setOrganizationCity("North Hampton");
        superDao.updateOrganization(superOrg);

        Organization fromDao = superDao.getOrganizationById(superOrg.getOrganizationId());

        assertEquals(fromDao, superOrg);
    }

    @Test
    public void TestGetAllOrganizationbySuperPersonId() {
        System.out.println("TEST GET ALL ORGANIZATIONS BY SUPER PERSON ID");
        //Arrange
        Organization superOrg = new Organization();
        
        Location location = new Location();
        location.setLocationName("London");
        location.setLocationDescription("Amazing");
        location.setLocationLong("30.2");
        location.setLocationLat("20.4");
        location.setLocationAddress("15 Parkside Place");
        location.setLocationId(700);
        
        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("John Boyle");
        superPerson.setDescription("Wolverine");
        superPerson.setSuperPower("Precognition");
        superPerson.setOrg(superOrg);
        superPerson.setLocation(location);
        superPerson.setSuperPersonId(102);       
        
        

        superOrg.setOrganizationName("SuperHero Land");
        superOrg.setOrganizationDescription("Super Sighting and Power");
        superOrg.setOrganizationAddress("256 Pleasant Street");
        superOrg.setOrganizationCity("Portsmouth");
        superOrg.setOrganizationState("New Hamsphire");
        superOrg.setOrganizationPhone("2624561846");
        superOrg.setOrganizationEmail("SHSIGHT225@LM.com");
        superOrg.setOrganizationZipode("02148");
        superOrg.setSuperPerson(superPerson);
        
        superDao.addOrganization(superOrg);

        Organization superOrg2 = new Organization();
        
        Location location2 = new Location();
        location2.setLocationName("Wichata");
        location2.setLocationDescription("Amazing");
        location2.setLocationLong("30.2");
        location2.setLocationLat("20.4");
        location2.setLocationAddress("15 Parkside Place");
        location2.setLocationId(800);
        
        SuperPerson superPerson2 = new SuperPerson();
        superPerson2.setName("Arsene");
        superPerson2.setDescription("Wolverine");
        superPerson2.setSuperPower("Precognition");
        superPerson2.setOrg(superOrg2);
        superPerson2.setLocation(location2);
        superPerson2.setSuperPersonId(103); 
        
        

        superOrg2.setOrganizationName("SuperHero Land");
        superOrg2.setOrganizationDescription("Super Sighting and Power");
        superOrg2.setOrganizationAddress("256 Pleasant Street");
        superOrg2.setOrganizationCity("Portsmouth");
        superOrg2.setOrganizationState("New Hamsphire");
        superOrg2.setOrganizationPhone("2624561846");
        superOrg2.setOrganizationEmail("SHSIGHT225@LM.com");
        superOrg2.setOrganizationZipode("02148");
        superOrg2.setSuperPerson(superPerson2);
        
        //ACT
        superDao.addOrganization(superOrg2);

        List<Organization> testOrgList = superDao.getAllOrganizationBySuperPersonId(103);

        //Assert
        assertEquals(testOrgList.size(), 1);
    }

}
