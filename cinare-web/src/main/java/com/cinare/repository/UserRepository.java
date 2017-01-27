package com.cinare.repository;

import com.cinare.model.User;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.Key;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UserRepository {

    public Key<User> save(User user) {
        return ofy().save().entity(user).now();
    }

    public User get(Long id) {
        return ofy().load().type(User.class).id(id).now();
    }

    public Key<User> update(User user) {
        return ofy().save().entity(user).now();
    }

    public void delete(User user) {
        ofy().delete().entity(user).now();
    }

    public List<User> list() {
        return ofy().load().type(User.class).list();
    }

    public List<User> list(String login) {
        return ofy().load().type(User.class)
                .order("age")
                .filter("login = ", login).list();
    }

    public List<User> listFilter(String login) {
        return ofy().load().type(User.class)
                .filter(new Query.FilterPredicate("login", Query.FilterOperator.EQUAL, login)).list();
    }

}
