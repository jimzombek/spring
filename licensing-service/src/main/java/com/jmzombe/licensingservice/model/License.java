package com.jmzombe.licensingservice.model;


/**
 * This is the model object that represents a License.
 */
//The @Document annotation identifies a domain object that is going to be persisted to MongoDB
//@Document
public class License {
	// Spring MVC will validate a @Valid object after binding so-long as an 
    // Validator has been configured. Note:The @Valid annotation is part of 
    // standard JSR-303 Bean Validation API, and is not a Spring-specific construct.
    //@Valid
    private String id;
    //@Valid
    private String organizationId;
    //@Valid
    private String productName;
    //@Valid
    private String licenseType;
   

    public License() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public License withId(String id){
        this.setId( id );
        return this;
    }

    public License withOrganizationId(String organizationId){
        this.setOrganizationId(organizationId);
        return this;
    }

    public License withProductName(String productName){
        this.setProductName(productName);
        return this;
    }

    public License withLicenseType(String licenseType){
        this.setLicenseType(licenseType);
        return this;
    }
}