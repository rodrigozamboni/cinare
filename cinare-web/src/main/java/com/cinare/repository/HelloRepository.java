package com.cinare.repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

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
}
