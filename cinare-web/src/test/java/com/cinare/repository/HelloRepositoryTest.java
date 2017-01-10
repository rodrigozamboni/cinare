package com.cinare.repository;

import com.google.appengine.api.datastore.Key;
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

}