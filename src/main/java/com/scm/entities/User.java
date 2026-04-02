package com.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @Column(length = 500)
    private String about;
    @Column(length = 500)
    private String profilePic;
    private String phoneNumber;

    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    
    //this is enum to identify the provider of the user whether its self or google or github
    private Providers provider= Providers.SELF;
    private String providerUserId; 


    //one user can have many contacts ,,, 
    // CascadeType.ALL means if we delete the user then all the contacts of that user will also be deleted
    //fetch = FetchType.LAZY __ means when we fetch the user then we will not fetch the contacts of that user until we explicitly call the getContacts() method
    //orphanRemoval = true means if we remove a contact from the user's contact list then that contact will also be deleted from the database
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();


}
