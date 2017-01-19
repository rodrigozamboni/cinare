package com.cinare.repository;

import com.google.appengine.api.datastore.*;

import java.util.Date;

public class HelloRepository {

    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public Key createNewEntity() {
        Entity jair = new Entity("Employee");
        jair.setProperty("firstName", "Antonio");
        jair.setProperty("lastName", "Salieri");
        jair.setProperty("hireDate", new Date());
        jair.setProperty("attendedHrTraining", true);
        return datastore.put(jair);
    }

    public Entity getEntity(Key key) throws EntityNotFoundException {
        return datastore.get(key);
    }

}
