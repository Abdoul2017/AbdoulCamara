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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author n0200797
 */
public class SuperPersonDaoDBImpl implements SuperPersonDao {

//*******************JDBC INJECTION*********************************************
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//*************************PREPARED STATEMENTS**********************************
    //SUPER PERSON PS
    private static final String SQL_INSERT_SUPERPERSON
            = "insert into SuperPerson "
            + "(name, description, superPower) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_SUPERPERSON
            = "delete from SuperPerson where superPersonId = ?";

    private static final String SQL_SELECT_ALL_SUPERPERSONS
            = "select * from SuperPerson";

    private static final String SQL_SELECT_SUPERPERSON
            = "select * from SuperPerson where superPersonId = ?";

    private static final String SQL_UPDATE_SUPERPERSON
            = "update SuperPerson set "
            + "name = ?, description = ?, superPower = ? "
            + "where superPersonId = ?";

    private static final String SQL_SELECT_SUPERPERSONS_BY_ORGANIZATION_ID
            = "select * from SuperPerson inner join superpersonorganization "
            + "on superpersonorganization.SuperPersonID = SuperPerson.SuperPersonID "
            + "inner join Organization on Organization.OrganizationID = superpersonorganization.OrganizationID "
            + "where Organization.OrganizationID = ? order by SuperPerson.name";

    private static final String SQL_SELECT_SUPERPERSONS_BY_LOCATION_ID
            = "select * from SuperPerson inner join Sighting "
            + "on SuperPerson.SuperPersonID "
            + "inner join Location on Location.LocationID = Sighting.LocationID "
            + "where location.LocationID = ? order by SuperPerson.name";

    //LOCATION PS
    private static final String SQL_INSERT_LOCATION
            = "insert into Location "
            + "(locationName, locationDescription, locationLong, locationLat, locationAddress) "
            + "values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from Location where locationId = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Location";

    private static final String SQL_SELECT_LOCATION
            = "select * from Location where locationId = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update Location set "
            + "locationName = ?, locationDescription = ?, "
            + "locationLong = ?, locationLat = ?, locationAddress = ? "
            + "where locationId = ?";

    //ORGANIZATION PS
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into Organization "
            + "(OrganizationName, OrganizationDescription, OrganizationAddress, "
            + "OrganizationCity, OrganizationState, OrganizationPhone, "
            + "OrganizationEmail, OrganizationZipCode) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from Organization where OrganizationId = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from Organization";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from Organization where organizationId = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update Organization set "
            + "organizationName = ?, organizationDescription = ?, "
            + "organizationAddress = ?, organizationCity = ?, "
            + "organizationState = ?, organizationPhone = ?, organizationEmail = ?, organizationZipCode = ? "
            + "where organizationId = ?";

    public static final String SQL_SELECT_ALL_ORGANIZATION_BY_SUPERPERSON_ID
            = "select * from Organization inner join SuperPersonOrganization "
            + "on SuperPersonOrganization.OrganizationID = Organization.OrganizationID"
            + "inner join SuperPerson on SuperPerson.SuperPersonID = SuperPersonOrganization.SuperPersonID"
            + "where SuperPerson.SuperPersonID = ? order by organization.organizationName";

    //SIGHTINGS PS
    private static final String SQL_INSERT_SIGHTING
            = "insert into Sighting "
            + "(sightingDate, locationId) "
            + "values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from Sighting where sightingId = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from Sighting where sightingId = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sighting";

    private static final String SQL_UPDATE_SIGHTING
            = "update Sighting set "
            + "sightingDate = ?, locationId = ? "
            + "where sightingId = ?";

    private static final String SQL_SELECT_SIGHTING_BY_DATE
            = "select * from Sighting where sightingDate = ?";

    private static final String SQL_SELECT_SIGHTING_BY_LOCATION_ID
            = "select * from Sighting where locationId = ?";

//**************************SUPER PERSON ABSTRACTS******************************
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperPerson addSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_INSERT_SUPERPERSON,
                superPerson.getName(),
                superPerson.getDescription(),
                superPerson.getSuperPower());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        superPerson.setSuperPersonId(newId);

        return superPerson;
    }

    @Override
    public SuperPerson deleteSuperPerson(int superPersonId) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSON, superPersonId);
        return null;
    }

    @Override
    public List<SuperPerson> getAllSuperPersons() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPERSONS, new SuperPersonMapper());
    }

    @Override
    public SuperPerson getSuperPersonById(int superPersonId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPERSON,
                    new SuperPersonMapper(), superPersonId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPERSON,
                superPerson.getName(),
                superPerson.getDescription(),
                superPerson.getSuperPower(),
                superPerson.getSuperPersonId());
    }

    @Override
    public List<SuperPerson> getSuperPersonByOrganization(int organizationId) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPERSONS_BY_ORGANIZATION_ID,
                new SuperPersonMapper(), organizationId);
    }

    @Override
    public List<SuperPerson> getSuperPersonByLocation(int LocationId) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPERSONS_BY_LOCATION_ID,
                new SuperPersonMapper(), LocationId);
    }

//**************************LOCATION ABSTRACTS**********************************
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationLong(),
                location.getLocationLat(),
                location.getLocationAddress());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        location.setLocationId(newId);

        return location;
    }

    @Override
    public Location deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
        return null;
    }

    @Override
    public void updateLocation(Location superLocation) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                superLocation.getLocationName(),
                superLocation.getLocationDescription(),
                superLocation.getLocationLong(),
                superLocation.getLocationLat(),
                superLocation.getLocationAddress(),
                superLocation.getLocationId());
    }

    @Override
    public Location getLocationByID(int locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

//**************************ORGANIZATION ABSTRACTS******************************
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationAddress(),
                organization.getOrganizationCity(),
                organization.getOrganizationState(),
                organization.getOrganizationPhone(),
                organization.getOrganizationEmail(),
                organization.getOrganizationZipode());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        organization.setOrganizationId(newId);

        return organization;
    }

    @Override
    public Organization deleteOrganization(int OrganizationId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, OrganizationId);
        return null;
    }

    @Override
    public void updateOrganization(Organization superOrg) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                superOrg.getOrganizationName(),
                superOrg.getOrganizationDescription(),
                superOrg.getOrganizationAddress(),
                superOrg.getOrganizationCity(),
                superOrg.getOrganizationState(),
                superOrg.getOrganizationPhone(),
                superOrg.getOrganizationEmail(),
                superOrg.getOrganizationZipode(),
                superOrg.getOrganizationId());
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrgMapper());
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrgMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizationBySuperPersonId(int superPersonId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATION_BY_SUPERPERSON_ID,
                new OrgMapper(), superPersonId);
    }

//**************************SIGHTINGS ABSTRACTS*********************************
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getSightingDate(),
                sighting.getLocation().getLocationId());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sighting.setSightingId(newId);

        return sighting;
    }

    @Override
    public Sighting deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
        return null;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getSightingDate(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
    }

    @Override
    public Sighting getSightingByID(int sightingId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(), sightingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_DATE, new SightingMapper(), date);
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_LOCATION_ID,
                new SightingMapper(), locationId);

    }
//*************************ROW MAPPERS******************************************

    //SuperPerson
    private static final class SuperPersonMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson superPerson = new SuperPerson();

            superPerson.setSuperPersonId(rs.getInt("superPersonId"));
            superPerson.setName(rs.getString("name"));
            superPerson.setDescription(rs.getString("description"));
            superPerson.setSuperPower(rs.getString("superPower"));

            return superPerson;
        }

    }

    //Location
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();

            location.setLocationId(rs.getInt("locationId"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setLocationLong(rs.getString("locationLong"));
            location.setLocationLat(rs.getString("locationLat"));
            location.setLocationAddress(rs.getString("locationAddress"));

            return location;
        }
    }

    //ORG
    private static final class OrgMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();

            org.setOrganizationId(rs.getInt("OrganizationID"));
            org.setOrganizationName(rs.getString("OrganizationName"));
            org.setOrganizationDescription(rs.getString("OrganizationDescription"));
            org.setOrganizationAddress(rs.getString("OrganizationAddress"));
            org.setOrganizationCity(rs.getString("OrganizationCity"));
            org.setOrganizationState(rs.getString("OrganizationState"));
            org.setOrganizationPhone(rs.getString("OrganizationPhone"));
            org.setOrganizationEmail(rs.getString("OrganizationEmail"));
            org.setOrganizationZipode(rs.getString("OrganizationZipCode"));

            return org;
        }
    }

    //SIGHTING
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();

            sighting.setSightingId(rs.getInt("SightingId"));
            sighting.setSightingDate(rs.getDate("SightingDate").toLocalDate());
            sighting.getLocation().setLocationId(rs.getInt("locationId"));

            return sighting;
        }

    }
}
