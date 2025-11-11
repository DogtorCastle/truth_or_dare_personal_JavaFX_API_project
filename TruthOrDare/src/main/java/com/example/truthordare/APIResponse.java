package com.example.truthordare;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIResponse {
    //Here's the API doc: https://docs.truthordarebot.xyz/api-docs
    public String type; // Requested in API call, so not needed
    public String id; // Not needed
    public String rating; // Requested in API call, so not needed
    public String question; // This is the good one
}
