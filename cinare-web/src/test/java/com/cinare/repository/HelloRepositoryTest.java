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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloRepositoryTest {

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

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
    public void testGetEntity() {
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

    @Test
    public void testUpdate() {
        HelloRepository hello = new HelloRepository();
        Key key = hello.createNewEntity();

        Entity jair = hello.getEntity(key);
        jair.setProperty("firstName", "JAIR UPDATE");

        key = hello.update(jair);

        jair = hello.getEntity(key);
        assertThat(jair.getProperty("firstName")).isEqualTo("JAIR UPDATE");
    }

    @Test
    public void testDelete() {
        HelloRepository hello = new HelloRepository();
        Key key = hello.createNewEntity();
        hello.delete(key);

        Entity jair = hello.getEntity(key);
        assertThat(jair).isNull();
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteException() throws EntityNotFoundException {
        HelloRepository hello = new HelloRepository();
        Key key = hello.createNewEntity();
        hello.delete(key);

        Entity jair = hello.getEntityException(key);
        assertThat(jair).isNull();
    }

    @Test
    public void testBuscarPorNome() {
        HelloRepository hello = new HelloRepository();
        hello.createNewEntity("Jair", "Bolsonaro", 50);
        hello.createNewEntity("Pedro", "Bolsonaro", 20);
        hello.createNewEntity("Luiz", "Veio", 30);
        hello.createNewEntity("Jair", "Plagtzimov", 50);

        List<Entity> jairs = hello.findByName("Jair");
        assertThat(jairs).isNotNull();
        assertThat(jairs.size()).isEqualTo(2);
    }

    @Test
    public void testBuscarPorNomeMaiorIdade() {
        HelloRepository hello = new HelloRepository();
        hello.createNewEntity("Jair", "Bolsonaro", 50);
        hello.createNewEntity("Pedro", "Bolsonaro", 20);
        hello.createNewEntity("Luiz", "Veio", 30);
        hello.createNewEntity("Jair", "Plagtzimov", 16);

        List<Entity> jairs = hello.findByNameAndMajority("Jair");
        assertThat(jairs).isNotNull();
        assertThat(jairs.size()).isEqualTo(1);
    }
}