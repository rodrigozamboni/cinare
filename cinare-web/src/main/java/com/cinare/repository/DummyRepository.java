package com.cinare.repository;

import com.google.appengine.api.datastore.*;

import java.util.Date;

public class DummyRepository {

    private static DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    public Key put(String name) {
        Entity employee = new Entity("Employee", "asalieri");
        employee.setProperty("firstName", name);
        employee.setProperty("lastName", "Salieri");
        employee.setProperty("hireDate", new Date());
        employee.setProperty("attendedHrTraining", true);

        return datastoreService.put(employee);
    }

    public Entity get(Key key) throws EntityNotFoundException {
        return datastoreService.get(key);
    }
}
