package com.cinare.repository;

import com.cinare.repository.AddressRepository.AddressProperties;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AddressRepositoryTest {

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    private Key parent = null;

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Before
    public void build() {
        HelloRepository hello = new HelloRepository();
        parent = hello.createNewEntity();
    }

    @Test
    public void testCreateNewEntity() {
        AddressRepository repo = new AddressRepository();
        Key addressKey = repo.create(parent);
        assertThat(addressKey).isNotNull();
        assertThat(addressKey.getId()).isEqualTo(2);
    }

    @Test
    public void testGetEntity() {
        AddressRepository repo = new AddressRepository();
        Key addressKey = repo.create(parent);

        Entity address = repo.getEntity(addressKey);
        assertThat(address).isNotNull();
        assertThat(address.getProperty(AddressProperties.STREET.toString())).isEqualTo("Rua Aquidaban");

        addressKey = KeyFactory.createKey(parent, AddressRepository.KIND, 2);
        address = repo.getEntity(addressKey);
        assertThat(address).isNotNull();
        assertThat(address.getProperty(AddressProperties.STREET.toString())).isEqualTo("Rua Aquidaban");
    }

    @Test
    public void testUpdate() {
        AddressRepository repo = new AddressRepository();
        Key addressKey = repo.create(parent);

        Entity address = repo.getEntity(addressKey);
        address.setProperty(AddressProperties.STREET.toString(), "Rua Vila Verde");
        addressKey = repo.update(address);

        address = repo.getEntity(addressKey);
        assertThat(address.getProperty(AddressProperties.STREET.toString())).isEqualTo("Rua Vila Verde");
    }

    @Test
    public void testDelete() {
        AddressRepository repo = new AddressRepository();
        Key addressKey = repo.create(parent);
        repo.delete(addressKey);

        Entity address = repo.getEntity(addressKey);
        assertThat(address).isNull();
    }

}