package com.cinare.repository;

import com.google.appengine.api.datastore.*;

import java.util.List;

public class AddressRepository {

    static final String KIND = "ADDRESS";
    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    enum AddressProperties {
        STREET, NUMBER, NEIGHBORHOOD, CITY, STATE;

    }

    public Key create(Key parent) {
        Entity address = new Entity(KIND, parent);
        address.setIndexedProperty(AddressProperties.STREET.toString(), "Rua Aquidaban");
        address.setIndexedProperty(AddressProperties.NUMBER.toString(), "123");
        address.setUnindexedProperty(AddressProperties.NEIGHBORHOOD.toString(), "Centro");
        address.setUnindexedProperty(AddressProperties.CITY.toString(), "Campinas");
        address.setUnindexedProperty(AddressProperties.STATE.toString(), "S\u00e3o Paulo");
        return datastore.put(address);
    }

    public Key create(Key parent, String street, int number, String neighborhood, String city, String state) {
        Entity address = new Entity(KIND, parent);
        address.setIndexedProperty(AddressProperties.STREET.toString(), street);
        address.setIndexedProperty(AddressProperties.NUMBER.toString(), number);
        address.setUnindexedProperty(AddressProperties.NEIGHBORHOOD.toString(), neighborhood);
        address.setUnindexedProperty(AddressProperties.CITY.toString(), city);
        address.setUnindexedProperty(AddressProperties.STATE.toString(), state);
        return datastore.put(address);
    }
    public Entity getEntity(Key addressKey) {
        try {
            return datastore.get(addressKey);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public Key update(Entity address) {
        return datastore.put(address);
    }

    public void delete(Key address) {
        datastore.delete(address);
    }

    public List<Entity> list(Key parent) {
        Query q = new Query(KIND).setAncestor(parent);
        PreparedQuery pq = datastore.prepare(q);
        return pq.asList(FetchOptions.Builder.withDefaults());
    }

    public List<Entity> list(Key parent, String street) {
        Query q = new Query(KIND)
                .setAncestor(parent)
                .setFilter(new Query.FilterPredicate(AddressProperties.STREET.toString(), Query.FilterOperator.EQUAL, street))
                .addSort(AddressProperties.NUMBER.toString(), Query.SortDirection.DESCENDING);
        PreparedQuery pq = datastore.prepare(q);
        return pq.asList(FetchOptions.Builder.withDefaults());
    }

}
