package com.amalitech.resource;

import com.amalitech.exceptions.ResourceFailsException;

public class ResourceThatFails implements AutoCloseable {

    public ResourceThatFails() {
    }

    @Override
    public void close() throws ResourceFailsException {
        throw new ResourceFailsException("Resource close() failed");
    }
}
