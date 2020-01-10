package com.appsdeveloperblog.app.ws.mobileappws.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// USER TABELL
@Entity(name="users") // Dette blir en egen tabell i databasen og her definerer vi navnet.
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -200424527156534837L;

    @Id // This id will be a primary key HIDDEN for others! This is to access data in db
    @GeneratedValue // vil bli autoincremented hver gang en new record er lagt inni db tabellen
    private long id;

    // This will be public userId we will send to the mobile app
    @Column(nullable=false) // Makes this field REQUIRED
    private String userId;

    @Column(nullable=false, length=50) // REQUIRED, VARCHAR LENGTH we set 50
    private String firstName;

    @Column(nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=120,  unique = true) // unique = true makes this field unique!
    private String email;

    @Column(nullable=false)
    private String encryptedPassword;

    private String emailVerificationToken;

    // Field that has Boolean 'false' as default value | SET IT IN DTO ALSO!
    @Column(nullable=false) // Kan sette i column, men vi gjør dette sånn at det kan fungere i forskjellige databaser
    private Boolean emailVerificationStatus = false;

    // One user can have MANY addresses, therefore it is one to many
    // Since users has a list of addresses we can say user OWNS addresses, therefore the parent. To define this we use mappedBy
    /**
     * @param mappedBy - Name of the field that is owning the relationship. Here we write userDetails field, since its the one connecting in AddressEntity
     * @param cascade - Hvis bruker er lagt inn med liste av addresses, så vil addresses også bli lagret i address tabellen. Samme gjelder når den blir slettet! Sletter du bruker så sletter den også addressene som tilhører
     * */
    @OneToMany(mappedBy="userDetails", cascade=CascadeType.ALL)
    private List<AddressEntity> addresses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }
}
