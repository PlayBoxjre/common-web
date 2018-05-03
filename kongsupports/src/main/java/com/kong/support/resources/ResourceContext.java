package com.kong.support.resources;

import com.kong.support.resources.defines.Resource;

public class ResourceContext {

    private Resource.OnResourceAccessListener listener;

    public Resource.OnResourceAccessListener getListener() {
        return listener;
    }

    public void setListener(Resource.OnResourceAccessListener listener) {
        this.listener = listener;
    }
}
