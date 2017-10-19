package com.jmzombe.licensingservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.jmzombe.licensingservice.config.ServiceConfig;
import com.jmzombe.licensingservice.model.License;
import com.jmzombe.licensingservice.services.LicenseService;

/**
 * This class serves as the {@link RestController} for serving up Licensing information.
 */

/* @RestController tells Spring Boot this is a REST-based service and will automatically serialize/deserialize
   service request/response to JSON.
*/
@RestController
/* Exposes all the HTTP endpoints in this class with a prefix of /v1/organizations/(organizationId}/licenses */
@RequestMapping("")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;
    
    @Autowired
	private ServiceConfig config;
    
    @RequestMapping(value="/licence/{licenseId}",method = RequestMethod.GET)
    public List<License> getLicenses(@PathVariable("licenseId") String licenseId) {
    	System.out.println(String.format("Calling getLicenses()"));
    	logConfigProperties();
        return licenseService.getLicenses(licenseId);
    }
    
    @RequestMapping(value="/{licenseId}",method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
         System.out.println(String.format("This is the get"));
         License license = licenseService.getLicense(licenseId);
         System.out.println(license.toString());
         return license;
    }
    
    public String updateLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        System.out.println(String.format("This is the put"));
        licenseService.updateLicense(new License());
        return String.format("License successfully updated.");
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.POST)
    public String saveLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        System.out.println(String.format("This is the post"));
        licenseService.saveLicense(new License());
        return String.format("License successfully saved.");
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        System.out.println(String.format("This is the delete"));
        licenseService.deleteLicense(new License());
        return String.format("License successfully deleted.");
    }
    
    // Print out auto-wired spring properties
    private void logConfigProperties() {
       	System.out.println("CONFIG ->Example: " + config.getExampleProperty());
     	System.out.println("CONFIG-> Database: " + config.getDatabase());
      	System.out.println("CONFIG-> Data Source: " + config.getDataSource());
        System.out.println("CONFIG-> Show SQL: " + config.getShowSQL());
        System.out.println("CONFIG-> Driver Class Name: " + config.getDriverClassName());
        System.out.println("CONFIG-> Data Source URL: " + config.getDataSourceURL());
        System.out.println("CONFIG-> User Name: " + config.getUserName());
        System.out.println("CONFIG-> User Password: " + config.getUserPassword());
        System.out.println("CONFIG-> Test While Idle: " + config.getTestWhilIdle());
        System.out.println("CONFIG-> Validation Query: " + config.getValidationQuery());
        System.out.println("CONFIG-> Hibernate Dialect: " + config.getHibernateDialect());
        System.out.println("CONFIG-> Redis Server: " + config.getRedisServer());
        System.out.println("CONFIG-> Redis Port: " + config.getRedisPort());
        System.out.println("CONFIG-> Signing Key: " + config.getSingingKey());
    }
 }