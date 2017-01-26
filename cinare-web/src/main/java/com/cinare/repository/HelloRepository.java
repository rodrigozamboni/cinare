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

    public void update(Entity ent) {
        datastore.put(ent);
    }

    public void delete(Key key) {
        datastore.delete(key);
    }

    public Entity getEntity(Key key) {
        try {
            return datastore.get(key);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

}
