package com.yesulikplim.missoftwaremgt.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple Jakarta EE 11 REST resource to test API availability.
 * Provides a basic endpoint that returns a JSON response when accessed.
 * 
 * @author YesuliKplim
 */
@Path("/health")
public class JakartaEE11Resource {

    /**
     * Health check endpoint to verify that the service is running.
     * 
     * @return A JSON response confirming API availability.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        // Create a structured JSON response
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("status", "success");
        responseMap.put("message", "Jakarta EE service is running");

        ResponseBuilder response = Response.ok(responseMap)
                                           .header("Content-Type", "application/json")
                                           .header("Access-Control-Allow-Origin", "*") // CORS support
                                           .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                                           .header("Access-Control-Allow-Headers", "Content-Type");

        return response.build();
    }
}
