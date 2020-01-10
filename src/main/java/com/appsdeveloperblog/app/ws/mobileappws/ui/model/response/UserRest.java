package com.appsdeveloperblog.app.ws.mobileappws.ui.model.response;

import java.util.List;

// Used in UI level, which is returned back to tell the user the information was successfully stored in the database (Tilsvarende view package lært av hamza)
// Here we define the values we want to return back to the UI, notice we dont have password here like we have in UserDetailsRequestMethod... we dont want to send password back
public class UserRest {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AddresseRest> addresses;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public List<AddresseRest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddresseRest> addresses) {
        this.addresses = addresses;
    }
}
