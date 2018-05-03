package com.kong.support.resources.defines;

import com.kong.support.exceptions.ResourceAccessException;

import java.net.URI;

public interface ResourceBundle {

    public Resource getResource(String path) throws ResourceAccessException;

    public Resource getResource(URI uri) throws ResourceAccessException;

    public Resource getResource(URI uri,Resource.OnResourceAccessListener listener) throws ResourceAccessException;

    public Resource getResource(String path,Resource.OnResourceAccessListener listener) throws ResourceAccessException;

    public void asyncGetResource(String path,Resource.OnResourceAccessListener onResourceAccessListener);

    public void asyncGetResource(URI uri,Resource.OnResourceAccessListener listener);

}
