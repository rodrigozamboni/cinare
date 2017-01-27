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

import java.util.List;

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

    @Test
    public void testBuscaEnderecosEmployee() {
        AddressRepository repo = new AddressRepository();
        repo.create(parent, "Rua 01", 111, "Bairro 01", "Cidade 01", "Estado 01");
        repo.create(parent, "Rua 02", 222, "Bairro 02", "Cidade 02", "Estado 02");
        repo.create(parent, "Rua 03", 333, "Bairro 03", "Cidade 03", "Estado 03");
        repo.create(parent, "Rua 04", 444, "Bairro 04", "Cidade 04", "Estado 04");
        repo.create(parent, "Rua 05", 555, "Bairro 05", "Cidade 05", "Estado 05");

        List<Entity> addresses = repo.list(parent);
        assertThat(addresses).isNotNull();
        assertThat(addresses.size()).isEqualTo(5);
    }

    @Test
    public void testBuscaEnderecosRuaOfEmployee() {
        AddressRepository repo = new AddressRepository();
        repo.create(parent, "Rua 01", 111, "Bairro 01", "Cidade 01", "Estado 01");
        repo.create(parent, "Rua 01", 222, "Bairro 01", "Cidade 01", "Estado 01");
        repo.create(parent, "Rua 03", 333, "Bairro 03", "Cidade 03", "Estado 03");
        repo.create(parent, "Rua 04", 444, "Bairro 04", "Cidade 04", "Estado 04");
        repo.create(parent, "Rua 01", 555, "Bairro 01", "Cidade 01", "Estado 01");

        List<Entity> addresses = repo.list(parent, "Rua 01");
        assertThat(addresses).isNotNull();
        assertThat(addresses.size()).isEqualTo(3);
        assertThat(addresses.get(0).getProperty(AddressProperties.NUMBER.toString())).isEqualTo(555L);
    }

}