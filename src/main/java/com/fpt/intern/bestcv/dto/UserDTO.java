package com.fpt.intern.bestcv.dto;

public class UserDTO {
    private String id;
    private String fullname;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String password;
    private int priorityAddress;
    private int industry;
    private int businessOrganization;
    private String provider;

    public UserDTO(String id, String fullname, String username, String email, String phone, String address, String password, int priorityAddress, int industry, int businessOrganization) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.priorityAddress = priorityAddress;
        this.industry = industry;
        this.businessOrganization = businessOrganization;
    }

    public UserDTO() {
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPriorityAddress() {
        return priorityAddress;
    }

    public void setPriorityAddress(int priorityAddress) {
        this.priorityAddress = priorityAddress;
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
    }

    public int getBusinessOrganization() {
        return businessOrganization;
    }

    public void setBusinessOrganization(int businessOrganization) {
        this.businessOrganization = businessOrganization;
    }
}
