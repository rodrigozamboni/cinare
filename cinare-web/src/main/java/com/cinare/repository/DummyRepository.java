package com.cinare.repository;

import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<Entity> list() {
        Query q = new Query("Person").setFilter(new Query.FilterPredicate("lastName", Query.FilterOperator.EQUAL, "Bla"));
        PreparedQuery pq = datastoreService.prepare(q);
        return pq.asList(FetchOptions.Builder.withDefaults());
    }

    public List<Entity> list2() {
        List<Query.Filter> filters = new ArrayList<>();
        filters.add(new Query.FilterPredicate("lastName", Query.FilterOperator.EQUAL, "Bla"));
        filters.add(new Query.FilterPredicate("lastName", Query.FilterOperator.EQUAL, "Bla"));

        Query q = new Query("Person").setFilter(new Query.CompositeFilter(Query.CompositeFilterOperator.AND, filters));
        PreparedQuery pq = datastoreService.prepare(q);
        return pq.asList(FetchOptions.Builder.withDefaults());
    }
}
