package com.cinare.repository;

import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelloRepository {

    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


    public Key createNewEntity(String firstName, String lastName, int age) {
        Entity jair = new Entity("Employee");
        jair.setIndexedProperty("firstName", firstName);
        jair.setIndexedProperty("age", age);
        jair.setUnindexedProperty("lastName", lastName);
        jair.setUnindexedProperty("hireDate", new Date());
        jair.setUnindexedProperty("attendedHrTraining", true);
        return datastore.put(jair);
    }

    public Key createNewEntity() {
        Entity jair = new Entity("Employee");
        jair.setProperty("firstName", "Antonio");
        jair.setProperty("lastName", "Salieri");
        jair.setProperty("hireDate", new Date());
        jair.setProperty("attendedHrTraining", true);
        return datastore.put(jair);
    }

    public Key update(Entity ent) {
        return datastore.put(ent);
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

<<<<<<< HEAD
    public List<Entity> findByName(String nome) {
        Query q = new Query("Employee").setFilter(new Query.FilterPredicate("firstName", Query.FilterOperator.EQUAL, nome));
        PreparedQuery pq = datastore.prepare(q);
        return pq.asList(FetchOptions.Builder.withDefaults());
    }

    public List<Entity> findByNameAndMajority(String name) {
        List<Query.Filter> filters = new ArrayList<>();
        filters.add(new Query.FilterPredicate("firstName", Query.FilterOperator.EQUAL, name));
        filters.add(new Query.FilterPredicate("age", Query.FilterOperator.GREATER_THAN_OR_EQUAL, 18));

        Query q = new Query("Employee")
                .setFilter(new Query.CompositeFilter(Query.CompositeFilterOperator.AND, filters));

        PreparedQuery pq = datastore.prepare(q);
        return pq.asList(FetchOptions.Builder.withDefaults());
    }
=======
    public Entity getEntityException(Key key) throws EntityNotFoundException {
        return datastore.get(key);
    }

>>>>>>> d89f4e5d59c5590c6be9ad1086cd06966819cb5a
}
