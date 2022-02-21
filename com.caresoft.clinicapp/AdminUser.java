package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {

//... imports class definition...
	
	 // Inside class:
    private String role;
    private ArrayList<String> securityIncidents;
	// TO DO: Implement a constructor that takes an ID and a role
	public AdminUser(Integer id, String role) {
		super(id);
		this.role = role;
		// Produced Null Pointer Error initially 
		// Null pointer exception typically means a list or something
		// similar was not initialized properly/ has an empty value - 
		// in this case null is okay because the list is empty.
		// error - we need to initialize the securityIncidents ArrayList.
		// This will initialize the ArrayList to a new, empty list. 
		this.securityIncidents = new ArrayList<String>();
	}
	
    
    
    // TO DO: Implement HIPAACompliantAdmin!
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
    
    // TO DO: Setters & Getters

	@Override
	public ArrayList<String> reportSecurityIncidents() {
		// TODO Auto-generated method stub
		// Returns a list of strings called incidents reported
		return this.securityIncidents;
	}

	@Override
	public boolean assignPin(int pin) {
		// TODO Auto-generated method stub
		// Pin must be 6 digits or more; returns false if not
		// Expected to assign the pin to the user (as a member variable)
		if (pin < 100000 || pin > 999999) {
		return false;
		}
		// assigning the pin to the user
		this.pin = pin;
		return true;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		// TODO Auto-generated method stub
		// Compares the id's, and if they are not a match,
		// creates an incident report using the  authIncident method,
		// Returns true if id's match, false if not.
		if (confirmedAuthID != this.id) {
			this.authIncident();
			return false;
		}
		return true;
	}

	
}
