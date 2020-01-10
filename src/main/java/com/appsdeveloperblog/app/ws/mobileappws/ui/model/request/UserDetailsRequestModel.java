package com.appsdeveloperblog.app.ws.mobileappws.ui.model.request;

import java.util.List;

// Tilsvarende 'db' package learned by hamza, see 'watercircles'
// Dette er hva JSON vil måtte inneholde!
public class UserDetailsRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressRequestModel> addresses; // User will hold list of addresses, one to many

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressRequestModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequestModel> addresses) {
        this.addresses = addresses;
    }
}
