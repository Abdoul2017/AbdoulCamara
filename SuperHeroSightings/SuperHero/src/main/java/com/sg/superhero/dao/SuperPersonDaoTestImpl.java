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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0200797
 */
public class SuperPersonDaoTestImpl implements SuperPersonDao {

    List<SuperPerson> superPersons = new ArrayList();
    List<Location> superLocations = new ArrayList();
    List<Organization> superOrgs = new ArrayList();
    List<Sighting> superSighting = new ArrayList();

//************************SUPER PERSON ABSTRACT METHODS*************************    
    @Override
    public SuperPerson addSuperPerson(SuperPerson superPerson) {
        superPersons.add(superPerson);
        return superPerson;
    }

    @Override
    public SuperPerson getSuperPersonById(int superPersonId) {
        for (SuperPerson superPerson : superPersons) {
            if (superPerson.getSuperPersonId() == superPersonId) {
                return superPerson;
            }
        }
        return null;
    }

    @Override
    public SuperPerson deleteSuperPerson(int superPersonId) {
        SuperPerson removedSuperPerson = superPersons.remove(superPersonId);
        return removedSuperPerson;
    }

    @Override
    public List<SuperPerson> getAllSuperPersons() {
        return superPersons;
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        superPersons.add(superPerson.getSuperPersonId(), superPerson);
    }

    @Override
    public List<SuperPerson> getSuperPersonByOrganization(int organizationId) {
        List<SuperPerson> superPersonByOrganizationId = new ArrayList<SuperPerson>();
        for (SuperPerson superP : superPersons) {
            if (superP.getOrg().getOrganizationId() == organizationId) {
                superPersonByOrganizationId.add(superP);
                return superPersonByOrganizationId;
            }
        }
        return null;
    }

    @Override
    public List<SuperPerson> getSuperPersonByLocation(int LocationId) {
        List<SuperPerson> superPersonByOrganizationId = new ArrayList<SuperPerson>();
        for (SuperPerson superLocal : superPersons) {
            if (superLocal.getLocation().getLocationId() == LocationId) {
                superPersonByOrganizationId.add(superLocal);
                return superPersonByOrganizationId;
            }
        }
        return null;
    }

//************************LOCATION ABSTRACT METHODS*****************************     
    @Override
    public Location addLocation(Location location) {
        superLocations.add(location);
        return location;
    }

    @Override
    public Location deleteLocation(int locationId) {
        Location removedLocation = superLocations.remove(locationId);
        return removedLocation;
    }

    @Override
    public void updateLocation(Location superLocation) {
        superLocations.add(superLocation.getLocationId(), superLocation);
    }

    @Override
    public Location getLocationByID(int locationId) {
        for (Location superLocation : superLocations) {
            if (superLocation.getLocationId() == locationId) {
                return superLocation;
            }
        }
        return null;
    }

    @Override
    public List<Location> getAllLocations() {
        return superLocations;
    }
//**********************ORGANIZATION ABSTRACT METHODS***************************  

    @Override
    public Organization addOrganization(Organization organization) {
        superOrgs.add(organization);
        return organization;
    }

    @Override
    public Organization deleteOrganization(int OrganizationId) {
        Organization removedOrg = superOrgs.remove(OrganizationId);
        return removedOrg;
    }

    @Override
    public void updateOrganization(Organization superOrg) {
        superOrgs.add(superOrg.getOrganizationId(), superOrg);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return superOrgs;
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        for (Organization superOrganization : superOrgs) {
            if (superOrganization.getOrganizationId() == organizationId) {
                return superOrganization;
            }
        }
        return null;
    }

    @Override
    public List<Organization> getAllOrganizationBySuperPersonId(int superPersonId) {
        List<Organization> orgmemberBySuperPersonId = new ArrayList<Organization>();
        for (Organization OrgsForSuperP : superOrgs) {
            if (OrgsForSuperP.getSuperPerson().getSuperPersonId() == superPersonId) {
                orgmemberBySuperPersonId.add(OrgsForSuperP);
                return orgmemberBySuperPersonId;
            }
        }
        return null;
    }
//**********************SIGHTING ABSTRACT METHODS*******************************      

    @Override
    public Sighting addSighting(Sighting sighting) {
        superSighting.add(sighting);
        return sighting;
    }

    @Override
    public Sighting deleteSighting(int sightingId) {
        Sighting removedSighting = superSighting.remove(sightingId);
        return removedSighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        superSighting.add(sighting.getSightingId(), sighting);
    }

    @Override
    public Sighting getSightingByID(int sightingId) {
        for (Sighting superSighting : superSighting) {
            if (superSighting.getSightingId() == sightingId) {
                return superSighting;
            }
        }
        return null;
    }

    @Override
    public List<Sighting> getAllSightings() {
        return superSighting;
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        List<Sighting> sightingByDate = new ArrayList<Sighting>();
        for (Sighting superSight : superSighting) {
            if (superSight.getSightingDate().equals(date)) {
                sightingByDate.add(superSight);
                return sightingByDate;
            }
        }
        return null;
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        List<Sighting> sightingByLocationId = new ArrayList<Sighting>();
        for (Sighting superSight : superSighting) {
            if (superSight.getLocation().getLocationId() == locationId) {
                sightingByLocationId.add(superSight);
                return sightingByLocationId;
            }
        }
        return null;
    }

}
