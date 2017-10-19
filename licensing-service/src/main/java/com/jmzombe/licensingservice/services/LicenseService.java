package com.jmzombe.licensingservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmzombe.licensingservice.config.ServiceConfig;
import com.jmzombe.licensingservice.model.License;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class LicenseService {
	@Autowired
	private ServiceConfig config;
	
    public License getLicense(String licenseId){
        return new License()
                .withId(licenseId)
                .withOrganizationId( UUID.randomUUID().toString() )
                .withProductName("jmzombe.com")
                .withLicenseType("PerSeat");
    }
    
    public List<License> getLicenses(String organizationId){
    	ArrayList<License> licenses = new ArrayList<License>();
    	
        License license =  new License()
                .withId(organizationId)
                .withOrganizationId( UUID.randomUUID().toString() )
                .withProductName("jmzombe.com")
                .withLicenseType("PerSeat");
        licenses.add(license);
        return licenses;
    }

    public void saveLicense(License license) {
        System.out.println(String.format("license saved"));
    }

    public void updateLicense(License license) {
        System.out.println(String.format("license updated"));
    }

    public void deleteLicense(License license) {
        System.out.println(String.format("license deleted"));
    }

    // what about config refresh calling the /sprint boot actuator refresh endpoint
}