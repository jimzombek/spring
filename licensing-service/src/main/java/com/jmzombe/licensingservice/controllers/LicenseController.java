package com.jmzombe.licensingservice.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
@RequestMapping(value="/v1/organization/{organizationId}/license")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;
    
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
 }