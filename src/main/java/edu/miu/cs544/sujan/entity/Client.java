package edu.miu.cs544.sujan.entity;

import javax.persistence.Entity;

@Entity
public class Client extends Company {
    private String companyMission;
    private String reason;
    private String website;

    public Client() {
        super();
    }

    public Client(String name, Address address, String companyMission, String reason, String website) {
        super(name, address);
        this.companyMission = companyMission;
        this.reason = reason;
        this.website = website;
    }

    public String getCompanyMission() {
        return companyMission;
    }

    public void setCompanyMission(String companyMission) {
        this.companyMission = companyMission;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}