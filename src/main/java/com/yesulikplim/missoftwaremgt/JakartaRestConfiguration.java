package com.yesulikplim.missoftwaremgt;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

import com.yesulikplim.missoftwaremgt.resources.JakartaEE11Resource; // Import your REST resource classes

/**
 * Configures Jakarta RESTful Web Services for the application.
 * Sets up the base API path and registers REST resources.
 * 
 * @author Yesuli Kplim
 */
@ApplicationPath("/api") // Standard base path for REST APIs
public class JakartaRestConfiguration extends Application {

    /**
     * Registers available REST resources explicitly.
     * Ensures only required resources are exposed.
     * 
     * @return a set of REST resource classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        
        // Register REST resources here
        resources.add(JakartaEE11Resource.class); 

        return resources;
    }
}
