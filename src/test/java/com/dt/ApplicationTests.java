package com.dt;

import com.dt.dao.FooDao;
import com.dt.enums.Baz;
import com.dt.model.Bar;
import com.dt.model.Foo;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private FooDao fooDao;

    @Test
    public void okDaoTest() {

        Foo foo = new Foo();
        fooDao.save(foo);

        ObjectId fooId = foo.getId();

        Bar bar = new Bar();
        bar.setBaz(Baz.B);
        bar.setBazMap(Collections.singletonMap(Baz.A, Baz.Z.name()));

        foo = fooDao.updateBar(fooId, bar);

        System.out.println(foo);

        Assert.assertEquals(Baz.B, foo.getBar().getBaz());
        Assert.assertEquals(Baz.Z.name(), foo.getBar().getBazMap().get(Baz.A));
    }

    @Test
    public void failedDaoTest() {

        Foo foo = new Foo();
        fooDao.save(foo);

        ObjectId fooId = foo.getId();

        Bar bar = new Bar();
        bar.setBaz(Baz.B);
        bar.setBazMap(Collections.singletonMap(Baz.A, Baz.Z.name()));

        foo = fooDao.updateBarMap(fooId, bar);

        System.out.println(foo);

        Assert.assertEquals(Baz.B, foo.getBar().getBaz());
        Assert.assertEquals(Baz.Z.name(), foo.getBar().getBazMap().get(Baz.A));
    }

    @Test
    public void trickyDaoTest() {

        Foo foo = new Foo();
        fooDao.save(foo);

        ObjectId fooId = foo.getId();

        Bar bar = new Bar();
        bar.setBaz(Baz.B);
        bar.setBazMap(Collections.singletonMap(Baz.A, Baz.Z.name()));

        foo = fooDao.updateBarMapTricky(fooId, bar);

        System.out.println(foo);

        Assert.assertEquals(Baz.B, foo.getBar().getBaz());
        Assert.assertEquals(Baz.Z.name(), foo.getBar().getBazMap().get(Baz.A));
    }

}
