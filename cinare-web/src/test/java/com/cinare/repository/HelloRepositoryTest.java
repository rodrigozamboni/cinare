package com.cinare.repository;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloRepositoryTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testCreateNewEntity() {
        HelloRepository hello = new HelloRepository();
        Key jair = hello.createNewEntity();
        assertThat(jair).isNotNull();
        assertThat(jair.getId()).isEqualTo(1);
        jair = hello.createNewEntity();
        assertThat(jair).isNotNull();
        assertThat(jair.getId()).isEqualTo(2);
    }

    @Test
    public void testGetEntity() throws EntityNotFoundException {
        HelloRepository hello = new HelloRepository();
        Key key = hello.createNewEntity();

        Entity jair = hello.getEntity(key);
        assertThat(jair).isNotNull();
        assertThat(jair.getProperty("firstName")).isEqualTo("Antonio");


        key = KeyFactory.createKey("Employee", 1);
        jair = hello.getEntity(key);
        assertThat(jair).isNotNull();
        assertThat(jair.getProperty("firstName")).isEqualTo("Antonio");
    }
}