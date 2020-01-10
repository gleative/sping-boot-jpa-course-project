package com.appsdeveloperblog.app.ws.mobileappws.io.entity;

import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="addresses")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 6757963427414876335L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length=50)
    private String addressId;

    @Column(nullable = false, length=15)
    private String city;

    @Column(nullable = false, length=15)
    private String country;

    @Column(nullable = false, length=100)
    private String streetName;

    @Column(nullable = false, length=7)
    private String postalCode;

    @Column(nullable = false, length=10)
    private String type;

    // Mange addresser kan tilhøre 1 user, one user kan ha mange addresser
    @ManyToOne
    @JoinColumn(name="users_id") // We connecter de, users_id fordi --> users er "users" table, og id er "id" feltet i users tabellen
    private UserEntity userDetails; // This is so we know what user these addresses are associated with

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }
}
