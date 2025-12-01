package com.amalitech.service;

import com.amalitech.exceptions.ExternalApiException;

public class ExternalApiService {

    public String callApi() {
        throw new ExternalApiException("External API timeout after 3000ms");
    }
}
