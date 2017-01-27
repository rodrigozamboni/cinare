package com.cinare.repository;

import com.cinare.model.User;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cache.AsyncCacheFilter;
import com.googlecode.objectify.util.Closeable;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UserRepositoryTest {

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private UserRepository repo;
    protected Closeable session;

    @BeforeClass
    public static void setUpBeforeClass() {
        ObjectifyService.setFactory(new ObjectifyFactory());
        ObjectifyService.register(User.class);
    }

    @Before
    public void setUp() {
        this.session = ObjectifyService.begin();
        this.helper.setUp();
        this.repo = new UserRepository();
    }

    @After
    public void tearDown() {
        AsyncCacheFilter.complete();
        this.session.close();
        this.helper.tearDown();
    }

    @Test
    public void testCreate() {
        User user = new User();
        user.setLogin("bolsomito");
        user.setPassword("bolsomito2018");
        user.setEmail("bolsonaro.jair@gmail.com");
        user.setFirstName("Jair");
        user.setLastName("Bolsonaro");
        user.setAge(50L);

        Key<User> key = repo.save(user);
        assertThat(key).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    public void testGet() {
        User user = new User();
        user.setLogin("bolsomito");
        user.setPassword("bolsomito2018");
        user.setEmail("bolsonaro.jair@gmail.com");
        user.setFirstName("Jair");
        user.setLastName("Bolsonaro");
        user.setAge(50L);
        repo.save(user);

        user = repo.get(1L);
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Jair");
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setLogin("bolsomito");
        user.setPassword("bolsomito2018");
        user.setEmail("bolsonaro.jair@gmail.com");
        user.setFirstName("Jair");
        user.setLastName("Bolsonaro");
        user.setAge(50L);
        repo.save(user);

        user = repo.get(1L);
        user.setAge(51L);

        repo.update(user);
        user = repo.get(1L);

        assertThat(user).isNotNull();
        assertThat(user.getAge()).isEqualTo(51L);
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setLogin("bolsomito");
        user.setPassword("bolsomito2018");
        user.setEmail("bolsonaro.jair@gmail.com");
        user.setFirstName("Jair");
        user.setLastName("Bolsonaro");
        user.setAge(50L);
        repo.save(user);

        repo.delete(user);

        user = repo.get(1L);
        assertThat(user).isNull();
    }

    @Test
    public void testList() {
        User user = new User();
        user.setLogin("bolsomito");
        user.setPassword("bolsomito2018");
        user.setEmail("bolsonaro.jair@gmail.com");
        user.setFirstName("Jair");
        user.setLastName("Bolsonaro");
        user.setAge(50L);
        repo.save(user);

        user = new User();
        user.setLogin("caiocavalo");
        user.setPassword("bolsomito2018");
        user.setEmail("caio.cavalo@gmail.com");
        user.setFirstName("Caio");
        user.setLastName("Cavalo");
        user.setAge(30L);
        repo.save(user);

        user = new User();
        user.setLogin("maconha_bob");
        user.setPassword("bolsomito2018");
        user.setEmail("legaliza_maconha@gmail.com");
        user.setFirstName("Marcelo");
        user.setLastName("Maconha");
        user.setAge(20L);
        repo.save(user);

        user = new User();
        user.setLogin("bolsomitojr");
        user.setPassword("bolsomito2018");
        user.setEmail("bolsonaro.jair.jr@gmail.com");
        user.setFirstName("Jair");
        user.setLastName("Bolsonaro Jr");
        user.setAge(18L);
        repo.save(user);

        List<User> list = repo.list();
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    public void testListFilter() {
        User user = new User();
        user.setLogin("bolsomito");
        user.setPassword("bolsomito2018");
        user.setEmail("bolsonaro.jair@gmail.com");
        user.setFirstName("Jair");
        user.setLastName("Bolsonaro");
        user.setAge(50L);
        repo.save(user);

        user = new User();
        user.setLogin("caiocavalo");
        user.setPassword("bolsomito2018");
        user.setEmail("caio.cavalo@gmail.com");
        user.setFirstName("Caio");
        user.setLastName("Cavalo");
        user.setAge(30L);
        repo.save(user);

        user = new User();
        user.setLogin("maconha_bob");
        user.setPassword("bolsomito2018");
        user.setEmail("legaliza_maconha@gmail.com");
        user.setFirstName("Marcelo");
        user.setLastName("Maconha");
        user.setAge(20L);
        repo.save(user);

        user = new User();
        user.setLogin("bolsomitojr");
        user.setPassword("bolsomito2018");
        user.setEmail("bolsonaro.jair.jr@gmail.com");
        user.setFirstName("Jair");
        user.setLastName("Bolsonaro Jr");
        user.setAge(18L);
        repo.save(user);

        List<User> list = repo.list("bolsomito");
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(1);

        list = repo.listFilter("bolsomito");
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(1);
    }

}