package com.cinare.repository;

import com.google.appengine.api.datastore.Key;
import org.junit.Ignore;

import static org.assertj.core.api.Assertions.assertThat;

public class DummyRepositoryTest {

    @Ignore
    public void create() throws Exception {
        Key key = new DummyRepository().put("Juca");
        assertThat(key).isNotNull();
    }

}