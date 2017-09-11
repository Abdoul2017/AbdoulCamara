/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SuperPerson;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0200797
 */
public interface SuperPersonDao {

    SuperPerson addSuperPerson(SuperPerson superPerson);

    SuperPerson deleteSuperPerson(int superPersonId);

    List<SuperPerson> getAllSuperPersons();

    SuperPerson getSuperPersonById(int superPersonId);

    void updateSuperPerson(SuperPerson superPerson);
    
    List<SuperPerson> getSuperPersonByOrganization(int organizationId);
    
    List<SuperPerson> getSuperPersonByLocation(int LocationId);
    
    
    

    Location addLocation(Location location);

    Location deleteLocation(int locationId);

    void updateLocation(Location superLocation);

    Location getLocationByID(int locationId);

    List<Location> getAllLocations();
    
    

    Organization addOrganization(Organization organization);

    Organization deleteOrganization(int OrganizationId);

    void updateOrganization(Organization superOrg);

    List<Organization> getAllOrganizations();

    Organization getOrganizationById(int organizationId);
    
    List<Organization> getAllOrganizationBySuperPersonId(int superPersonId);
    
    
    

    
    
    
    Sighting addSighting(Sighting sighting);

    Sighting deleteSighting(int sightingId);

    void updateSighting(Sighting sighting);

    Sighting getSightingByID(int sightingId);

    List<Sighting> getAllSightings();

    List<Sighting> getSightingsByDate(LocalDate date);
    
    List<Sighting> getSightingsByLocationId(int locationId);

}
