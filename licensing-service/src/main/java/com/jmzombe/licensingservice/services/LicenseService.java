package com.jmzombe.licensingservice.services;

import org.springframework.stereotype.Service;

import com.jmzombe.licensingservice.model.License;

import java.util.UUID;

@Service
public class LicenseService {
    public License getLicense(String licenseId){
        return new License()
                .withId(licenseId)
                .withOrganizationId( UUID.randomUUID().toString() )
                .withProductName("jmzombe.com")
                .withLicenseType("PerSeat");
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